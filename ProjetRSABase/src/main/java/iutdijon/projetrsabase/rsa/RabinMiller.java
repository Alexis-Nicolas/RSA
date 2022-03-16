package iutdijon.projetrsabase.rsa;

import static java.lang.Math.random;


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
        boolean b =false;
        int e = Integer.parseInt(n.toString(),2);
        for (int i=0;i<25;i++){
            int a=  (int) (random()*((e-2)-2));
            if(temoin(n,new NombreBinaire(a))==false){
                b=true;
                break;
            }

        }
        return b;
    }
    
    //DEFI 23 - Renvoie le plus petit nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
        return null;
    }
}
