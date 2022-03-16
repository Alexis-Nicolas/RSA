package iutdijon.projetrsabase.rsa;


/**
 * Description de la classe
 * @author Matthieu
 */
public class RabinMiller {

    
    //DEFI 16 - Méthode renvoyant si a est un témoin de Miller de n (preuve que n est composé)
    public static boolean temoin(NombreBinaire n, NombreBinaire a) {
        
        boolean res = true;
        NombreBinaire d = n.soustraction(new NombreBinaire(1));
        int s = 1;
        
        while(d.estPair()){
            d = d.decalageDroit(1);
            s++;
        }
        
        NombreBinaire x = a.puissanceModulo(d, n);
        
        if(x.estEgal(new NombreBinaire(1))){
            res = false;
        }
        
        for(int i=0;i<s-1;i++){
            x = x.puissanceModulo(new NombreBinaire(2), n);
            
            if(x.estEgal(n.soustraction(new NombreBinaire(1)))){
                res = false;
            }
        }
        
        return res;
    }
    
    //DEFI 19 - Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) {
        return false;
    }
    
    //DEFI 23 - Renvoie le plus petit nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
        return null;
    }
}
