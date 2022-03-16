package iutdijon.projetrsabase.defis;

import iutdijon.projetrsabase.defis.realisations.*;

/**
 * Fabrique des défis
 * @author Matthieu
 */
public class DefiFabrique {
    
    /**
     * Crée le défi
     * @param numeroDuDefis numéro du défi à créer
     * @return le défi
     */
    public static Defi creer(int numeroDuDefis) {
        Defi defi = null;
        
        switch(numeroDuDefis) {
            case 1 : defi = new Defi1ConnexionAuServeur(); break;
            case 2 : defi = new DefiAddition(); break;
            case 3 : defi = new Defi3Decalage(); break;
            case 4 : defi = new DefiSoustraction(); break;
            case 5 : defi = new Defi5EstInferieur(); break;
            case 6 : defi = new Defi6EstEgal(); break;
            case 7 : defi = new Defi7EstPair(); break;
            case 8 : defi = new Defi8Multiplication(); break;
            case 9 : defi = new Defi9Quotient(); break;
            case 10: defi = new Defi10Modulo(); break;
            case 11: defi = new Defi11RandomTailleFixee(); break;
            case 12: defi =  new Defi12PuissanceModulo(); break;
            case 13: defi = new Defi13PGCD(); break;
            case 14: defi = new Defi14RandomBornes(); break;
            case 15: defi = new Defi15InverseModulaire(); break;
            case 16: throw new UnsupportedOperationException("Défis non implémenté !");
            case 17: throw new UnsupportedOperationException("Défis non implémenté !");
            case 18: defi = new Defi18Dechiffrer();break;
            case 19: throw new UnsupportedOperationException("Défis non implémenté !");
            case 20: defi = new Defi20ClePrive(); break;
            case 21: throw new UnsupportedOperationException("Défis non implémenté !");
            case 22: defi = new Defi22Dechiffrer(); break;
            case 23: throw new UnsupportedOperationException("Défis non implémenté !");
            case 24: throw new UnsupportedOperationException("Défis non implémenté !");
            case 25: throw new UnsupportedOperationException("Défis non implémenté !");
            default : throw new UnsupportedOperationException("Défis non implémenté !");
        }
        
        return defi;
    }
    
}
