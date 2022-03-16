package iutdijon.projetrsabase.rsa;

/**
 * Description de la classe
 * @author Matthieu
 */
public class AlgorithmeRSA {

    
    //DEFI 17 - Chiffre un morceau (entrée : tailleMorceau, sortie : tailleCle)
    public static NombreBinaire chiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire e){
      return null;
    }
   
    //DEFI 18 - Déchiffre un morceau (entrée : tailleCle, sortie : tailleMorceau)
    public static NombreBinaire dechiffrerMorceau(NombreBinaire morceau, NombreBinaire N, NombreBinaire d){
        NombreBinaire res = new NombreBinaire();
        res = morceau.puissanceModulo(d, N);
        res.forcerTaille(d.getTaille());
        return res;
    }

    //DEFI 21 - Chiffre le message avec les clés données
    public static NombreBinaire chiffrer(NombreBinaire messageAChiffrer, NombreBinaire N, NombreBinaire e) {
        return null;
    }

    //DEFI 22 - Déchiffre le message avec les clés données
    public static NombreBinaire dechiffrer(NombreBinaire messageADechiffrer, NombreBinaire N, NombreBinaire d) {
        NombreBinaire res=new NombreBinaire();
        NombreBinaire temp=  new NombreBinaire();
        int taille = 1;
        if(d.getTaille()!=0){
            taille=messageADechiffrer.getTaille()/d.getTaille();
        }
        System.out.println("d = " + d.getTaille());
      
        for(int h=0; h <taille;h++){
            for(int i=0;i<d.getTaille();i++){
                temp.set(i, messageADechiffrer.get(i));
            }
            messageADechiffrer.decalageDroit(d.getTaille());
            temp=dechiffrerMorceau(temp, N, d);
            for(int j=res.getTaille();j<res.getTaille()+temp.getTaille();j++){
                res.set(j,temp.get(j));
            }
            temp=new NombreBinaire();
        }
        
        System.out.println(res.getTaille());
        return res;
    }
}