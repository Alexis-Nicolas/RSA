package iutdijon.projetrsabase.rsa;

/**
 * Générateur de clé RSA
 * @author Matthieu
 */
public class GenerateurDeClesRSA {

    private static NombreBinaire P;
    private static NombreBinaire Q;
    private static NombreBinaire N;
    private static NombreBinaire phi;
    private static NombreBinaire e;

    public static NombreBinaire getP() {
        return P;
    }

    public static NombreBinaire getQ() {
        return Q;
    }

    public static NombreBinaire getN() {
        return N;
    }
    
    /**
     * (P − 1)(Q − 1)
     * @return le nombre binaire phi
     */
    public static NombreBinaire getPhi() {
        return phi;
    }

    public static NombreBinaire getE() {
        return e;
    }

    public static void setP(NombreBinaire P) {
        GenerateurDeClesRSA.P = P;
    }

    public static void setQ(NombreBinaire Q) {
        GenerateurDeClesRSA.Q = Q;
    }

    public static void setPhi(NombreBinaire phi) {
        GenerateurDeClesRSA.phi = phi;
    }

    public static void setE(NombreBinaire e) {
        GenerateurDeClesRSA.e = e;
    }
    
    
    
    //Défi 24 - Génère la clé publique (P,Q,N,phi et e)    
    public static void genererClePublique() {
        
    }
    
    //Défi 20 - Renvoie la clé privée d
    public static NombreBinaire genererClePrive(NombreBinaire P,NombreBinaire Q,NombreBinaire e) 
    {
        GenerateurDeClesRSA.P = P;
        GenerateurDeClesRSA.Q = Q;
        GenerateurDeClesRSA.e = e;
        NombreBinaire nbBinaire1 = new NombreBinaire(1);
        GenerateurDeClesRSA.phi = 
                (P.soustraction(nbBinaire1)).multiplication(Q.soustraction(nbBinaire1));
        NombreBinaire clePrive = null;
        clePrive = e.inverseModulaire(phi);
        clePrive.forcerTaille(ParametresRSA.getTailleCle());
        return clePrive;
    }  
}
