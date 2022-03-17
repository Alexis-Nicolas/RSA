package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;

/**
 * Description de la classe
 * @author Matthieu
 */
public class AlgorithmeRSA {

    
    //DEFI 17 - Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public static NombreBinaire chiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire e)
    {
      NombreBinaire morceauChiffre = morceau.puissanceModulo(e, N);
      morceauChiffre.forcerTaille(ParametresRSA.getTailleCle());
      return morceauChiffre;
    }
   
    //DEFI 18 - Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public static NombreBinaire dechiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire d){
        NombreBinaire res = new NombreBinaire();
        res = morceau.puissanceModulo(d, N);
        res.forcerTaille(d.getTaille());
        System.out.println("Morceau déchiffré : "+res.toString());
        return res;
    }

    //DEFI 21 - Chiffre le message avec les clés données
    public static NombreBinaire chiffrer(NombreBinaire messageAChiffrer, NombreBinaire N, NombreBinaire e) 
    {
        ArrayList<NombreBinaire> nombreBinaireScinde =
                messageAChiffrer.scinder(ParametresRSA.getTailleMorceau());
        NombreBinaire nombreBinaireChiffre = new NombreBinaire();
        for(NombreBinaire morceau: nombreBinaireScinde)
        {
            nombreBinaireChiffre = 
                    nombreBinaireChiffre.concatenation(chiffrerMorceau(morceau, N, e));
        }
        return nombreBinaireChiffre;
    }

    //DEFI 22 - Déchiffre le message avec les clés données
    public static NombreBinaire dechiffrer(NombreBinaire messageADechiffrer, NombreBinaire N, NombreBinaire d) {
        NombreBinaire res=new NombreBinaire();
        NombreBinaire temp = new NombreBinaire();
        NombreBinaire morceau = new NombreBinaire();
        ArrayList<NombreBinaire> Nb = messageADechiffrer.scinder(d.getTaille());
        for(int i=0;i<Nb.size();i++){
            morceau = Nb.get(i);
            morceau = AlgorithmeRSA.dechiffrerMorceau(morceau, N, d);
            res=res.concatenation(morceau);
            System.out.println("Message : "+res.toString());
        }
        return res;
    }
}