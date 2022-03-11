package iutdijon.projetrsabase.rsa;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Description de la classe
 * @author Matthieu
 */
public class NombreBinaire {
    
    /**
     * Liste des bits du nombre binaire
     */
    private BitSet listeBits;
    
    /**
     * Taille forcée
     */
    private int tailleForcee = -1;
    
    // ------- METHODES DEJA IMPLEMENTEES --------------------------------------
    //Set un bit
    public void set(int i, boolean valeur) {
        this.listeBits.set(i,valeur);
    }
    
    //Get un bit
    public boolean get(int i) {
        return this.listeBits.get(i);
    }
    
    
    //Constructeurs standard
    public NombreBinaire() {
        this.listeBits = new BitSet();
    }
    
    //Constructeur clone
    public NombreBinaire(NombreBinaire nombre) {
        this.listeBits = new BitSet();
        for(int i=0;i<nombre.listeBits.length();i++) {
            this.listeBits.set(i,nombre.listeBits.get(i));
        } 
    }
    
    //Constructeur à partir d'un int
    public NombreBinaire(int valeur) {
        this.listeBits = new BitSet();
        int i = 0;
        while(valeur != 0) {
            this.listeBits.set(i,valeur%2==1);
            valeur /= 2;
            i++;
        }
    }
    
    
    //Constructeur à partir d'une chaine de caractère binaire
    public NombreBinaire(String s) {
        this();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(s.length()-i-1) == '1') {
                this.listeBits.set(i,true);
            }
        }
    }
    
    //Renvoie la taille (en nb de bits)
    public int getTaille() {
        int valeur = 0;
        if(this.tailleForcee == -1) valeur =  this.listeBits.length();
        else valeur = this.tailleForcee;
        return valeur;
    }
    
    //Force la taille (en nb de bits)
    public void forcerTaille(int valeur) {
        this.tailleForcee = valeur;
    }
    
    
    //Affichage
    @Override
    public String toString() {
        String res = "";
        for(int i=0;i<this.getTaille();i++) {
            if(this.listeBits.get(i)) {
                res = "1"+res;
            }
            else {
                res = "0"+res;
            }
        }
        if("".equals(res)) {
            res = "0";
        }
        return res;
    }
    
    
     //Scinde un nombreBinaire en nombreBinaire de taille donnée
     public ArrayList<NombreBinaire> scinder(int tailleMorceau) {
        ArrayList<NombreBinaire> res = new ArrayList<>();
        for(int i=0;i<this.getTaille();i=i+tailleMorceau) {
            NombreBinaire bitset = new NombreBinaire();
            for(int j=0;j<tailleMorceau;j++) {
                bitset.set(j,this.listeBits.get(i+j));
            }
            NombreBinaire nb = new NombreBinaire(bitset);
            nb.tailleForcee = tailleMorceau;
            res.add(nb);
        }
        return res;
    }
     
    //Concaténation de deux nombre binaires de taille : tailleMorceau
     public NombreBinaire concatenation(NombreBinaire mot) {
         NombreBinaire sortie = new NombreBinaire(this);
         int taille = this.getTaille()+mot.getTaille();
         for(int i=0;i<taille;i++) {
             if(i<this.getTaille()) sortie.listeBits.set(i,this.listeBits.get(i));
             else sortie.listeBits.set(i,mot.listeBits.get(i-this.getTaille()));
         }
         sortie.forcerTaille(taille);
         return sortie;
     }
     
     //-------------------------------------------------------------------------
     
     //DEFI 2 - Renvoie le résultat de l'addition de this avec mot2
     public NombreBinaire addition(NombreBinaire mot2) {
         int R =0;
         ArrayList<NombreBinaire> nb1 = this.scinder(1);
         ArrayList<NombreBinaire> nb2 = mot2.scinder(1);
         int b1 ;
         int b2 ;
         int resI=1;
         String res = "";
         int i = 0;
         while(resI!=0||i<=this.getTaille()||i<=mot2.getTaille()){
             if(i<this.getTaille()){
                b1 = Integer.parseInt(nb1.get(i).toString());
             }
             else{
                 b1=0;
             }
             if(i<mot2.getTaille()){
                b2 = Integer.parseInt(nb2.get(i).toString());
             }
             else{
                 b2=0;
             }
             resI = b1+b2+R;
             if(resI==0){
                 res+="0";
                 R=0;
             }
             if(resI==1){
                 res+="1";
                 R=0;
             }
             if(resI==2){
                 res+="0";
                 R=1;
             }
             if(resI==3){
                 res+="1";
                 R=1;
             }
             i++;
         }
         StringBuilder strb = new StringBuilder(res);
         res = strb.reverse().toString();
         NombreBinaire nbb = new NombreBinaire(res);
        return nbb;
     }
     
     //DEFI 3 - Caclule le décalage de n bits (multiplie par 2^n)
     public NombreBinaire decalage(int n) {
        NombreBinaire nb = new NombreBinaire(this);
        this.forcerTaille(this.getTaille()+ n);
        
        for(int i = 0 ; i < this.getTaille(); i++){
            if(i < n){
                set(i,false);
            }else{
               set(i,nb.get(i-n)); 
            }
        } 
        return this;
     }
     
     //DEFI 4 - renvoie le resultat de l'addition de this avec mot3
     public NombreBinaire soustraction(NombreBinaire mot2) {
        NombreBinaire R = new NombreBinaire();
         int retenu = 0;
         int bit1  =0;
         int bit2 =0;
         int val = 0;
         int result =0;
         boolean b1 = false;
         boolean b2 = false;
         if (mot2.getTaille()<this.getTaille()){
             val = this.getTaille()-mot2.getTaille();
             for(int i = 0;i<val;i++){
                 mot2.set(mot2.getTaille()+i, false);
             }
         }
         for(int i = 0;i<this.getTaille();i++){
             
            b1= this.get(i);
            b2=mot2.get(i);
            if (b1){
                bit1 = 1;
            }else if (!b1){
                bit1=0;
            }
            if (b2){
                bit2 = 1;
            }else if (!b2){
                bit2=0;
            }
            if ((bit1-bit2-retenu)==0){
                R.set(i, false);
                retenu=0;
            }else if ((bit1-bit2-retenu)==1){
                R.set(i, true);
                retenu=0;
            }else if ((bit1-bit2-retenu)==-1){
                R.set(i, true);
                retenu=1;
            }else if ((bit1-bit2-retenu)==-2){
                R.set(i, false);
                retenu=1;
            }
            
             
         }
        return R;
     }
     
     //DEFI 5 - Renvoie si this est plus petit ou égal à mot2
     public boolean estInferieurA(NombreBinaire mot2) {
         return false;
     }
     
     //DEFI 6 - Renvoie si this est égal à mot2 ou non
     public boolean estEgal(NombreBinaire mot2) {
		 
        // Initialisation
        boolean res = false;
        String strPrem = this.toString();
        String strSec = mot2.toString();
        
        // Teste si les deux chaînes sont égales
        if(strPrem.equals(strSec)){
            res = true;
        }
         
        return res;
     }
     
     //DEFI 7 - Renvoie si un nombre est pair
     public boolean estPair() {
        boolean res = true;
         
        if(this.get(0)){
            res = false;
        }
         
        return res;
     }
     
     //DEFI 8 - Calcul la multiplication de this avec mot2
     public NombreBinaire multiplication(NombreBinaire mot2) {
        return null;
     }

     //DEFI 9 - Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2) {
        return null;
     }
     
     //DEFI 10 - Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) {
         return null;
     }  
     
    //DEFI 11 - Génère un nombre binaire aléatoire de "taille" bits au maximum.
    public static NombreBinaire randomAvecTailleMax(int taille) {
        String nb ="";
        for (int i=0; i < taille; i++){
            String nombreRandom = String.valueOf(0 + (int)(Math.random() * ((1 - 0) + 1)));
            nb += nombreRandom;
        }
        
        return new NombreBinaire(nb);
    }
    
     //DEFI 12 - Calcul de this^exposant modulo m par exponentiation modulaire rapide
     public NombreBinaire puissanceModulo(NombreBinaire exposant, NombreBinaire m) {
         return null;
     }
     
     //DEFI 13 - Calcul le PGCD de this et mot2
     public NombreBinaire PGCD(NombreBinaire mot2) {
        return null;
     }
     
    //DEFI 14 - renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {
        //Utiliser les fonctions soustractions et additions ?
        
        return null;
    }
    
     //DEFI 15 - Calcul de l'inverse modulo nombre
     //Basé sur l'algo d'euclide étendu (adapté).
     public NombreBinaire inverseModulaire(NombreBinaire nombre) {
         NombreBinaire ZERO = new NombreBinaire(0);
            
         NombreBinaire n0 = new NombreBinaire(nombre);
         NombreBinaire b0 = new NombreBinaire(this);
         NombreBinaire t0 = new NombreBinaire(0);
         NombreBinaire t = new NombreBinaire(1);
         
         NombreBinaire q = n0.quotient(b0);
         NombreBinaire r = n0.modulo(b0);
         while(!r.estEgal(ZERO)) {
             NombreBinaire produit = q.multiplication(t);
             NombreBinaire memoire;
             //Gére le fait qu'un nombreBinaire ne peut pas être négatif......
             if(t0.estInferieurA(produit)) {
                memoire = nombre.soustraction(produit.soustraction(t0).modulo(nombre));
             }
             else {
                memoire = t0.soustraction(produit).modulo(nombre);  
             }
             
             t0 = t;
             t = memoire;
             n0 = b0;
             b0 = r;
             q = n0.quotient(b0);
             r = n0.modulo(b0);
         }
         return t;
     }
     
}
