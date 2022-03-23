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
        NombreBinaire un = n.soustraction(new NombreBinaire(1));
        NombreBinaire d= un;
        int s = 0;
        
        while(d.estPair()){
            d = d.quotient(new NombreBinaire(2));
            s++;
        }
        
        NombreBinaire x = a.puissanceModulo(d, n);
        
        if(x.estEgal(new NombreBinaire(1)) || x.estEgal(un)){
            res = false;
        }
        
        for(int i=0;i<s-1;i++){
            x = x.puissanceModulo(new NombreBinaire(2), n);
            
            if(x.estEgal(un)){
                res = false;
            }
        }
        
        return res;
    }
    
    //DEFI 19 - Test de RabinMiller, test probabilistiquement que n est premier (proba erreur = 1/4^k)
    public static boolean testRabinMiller(NombreBinaire n) {        
        
        boolean b = true;
        NombreBinaire n2 = new NombreBinaire(2);
        NombreBinaire ns2 = n.soustraction(n2);

        for (int i=0;i<25;i++){

           NombreBinaire a = NombreBinaire.random(n2, ns2);

           if(RabinMiller.temoin(n, a)){
               b = false;
               break;
           }
        }

        return b;
    }
    
    //DEFI 23 - Renvoie le plus petit nombre premier supérieur à min
    public static NombreBinaire nombrePremier(NombreBinaire min) {
        
        // Initialisation
        boolean isPrime = false;
        NombreBinaire un = new NombreBinaire(1);
        
        // Tant que le nombre n'est pas premier
        while(!isPrime){
            
            // S'il est pair on n'a pas besoin de tester s'il est premier
            if(min.estPair()){
                min = min.addition(un);
            }else{
                // Si le teste passe on renvoie le résultat sinon on incrémente
                if(RabinMiller.testRabinMiller(min)){
                    isPrime = true;
                }else{
                    min = min.addition(un);
                }
            }
        }
        
        return min;
    }
}
