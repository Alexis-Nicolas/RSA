package iutdijon.projetrsabase.rsa;

import static java.lang.Math.abs;
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
         NombreBinaire R = new NombreBinaire();
         int retenu = 0;
         int bit1  =0;
         int bit2 =0;
         int val = 0;
         int result =0;
         int i=0;
         boolean b1 = false;
         boolean b2 = false;
         if (mot2.getTaille()<this.getTaille()){
             val = this.getTaille()-mot2.getTaille();
             for(int j = 0;j<val;j++){
                 mot2.set(mot2.getTaille()+j, false);
             }
         }
         while(i<=this.getTaille()||i<=mot2.getTaille()){
             
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
            if ((bit1+bit2+retenu)==0){
                R.set(i, false);
                retenu=0;
            }else if ((bit1+bit2+retenu)==1){
                R.set(i, true);
                retenu=0;
            }else if ((bit1+bit2+retenu)==2){
                R.set(i, false);
                retenu=1;
            }else if ((bit1+bit2+retenu)==3){
                R.set(i, true);
                retenu=1;
            }
            i++;
             
         }
        return R;
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
	 
	 //DEFI 3 - bis
     public NombreBinaire decalageDroit(int n) {
        NombreBinaire nb = new NombreBinaire(this);
        this.forcerTaille(this.getTaille()- n);
        
        for(int i = 0 ; i < this.getTaille(); i++){
            set(i,nb.get(i+1)); 
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
        boolean estInferieur = false;
        if(this.getTaille()-1 < mot2.getTaille()-1)
        {
            estInferieur = true;
        }
        else if(this.getTaille()-1 == mot2.getTaille()-1)
        {
            int numBit = this.getTaille()-1;
            boolean comparaisonFinie = false;
            //on compare les nombres bit à bit
            while((numBit >= 0) && (!comparaisonFinie))
            {
                //mot 1 inférieur
                if((this.get(numBit) == false) && (mot2.get(numBit)==true))
                {
                    estInferieur = true;
                    comparaisonFinie = true;
                }
                //mot 2 inférieur
                else if((this.get(numBit) == true) && (mot2.get(numBit) == false))
                {
                    comparaisonFinie = true;
                }
                numBit --;
            }
        }
        return estInferieur;
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
        NombreBinaire a = new NombreBinaire();
        NombreBinaire c = new NombreBinaire();
        NombreBinaire b = new NombreBinaire();
        for(int i =0;i<this.getTaille();i++){
            if (this.get(i)){
                c=new NombreBinaire(mot2);
                a = c.decalage(i);
                b=b.addition(a);
            }
        }
        return b;
     }

     //DEFI 9 - Calcul le quotient dans la division euclidienne de this par mot2
     public NombreBinaire quotient(NombreBinaire mot2) {
          NombreBinaire r = new NombreBinaire(this);
           NombreBinaire b = new NombreBinaire(mot2);
           int i = 0;
        NombreBinaire q = new NombreBinaire(0);
        int n = 0;
        while (!r.estInferieurA(b)){
        n = abs(r.getTaille()-b.getTaille());
        NombreBinaire b2 = new NombreBinaire(b);
        b2.decalage(n);
        if (r.estInferieurA(b2)){
           b2 = new NombreBinaire(b);
            b2.decalage(n-1);
            n = n-1;
        }
        r=r.soustraction(b2);
        NombreBinaire d = new NombreBinaire((int) Math.pow(2, n));
        q =q.addition(d);
        }
        
        return q;
     }
     
     //DEFI 10 - Calcul this modulo mot2 via une division euclidienne
     public NombreBinaire modulo(NombreBinaire mot2) {
        NombreBinaire r = new NombreBinaire(this);
           NombreBinaire b = new NombreBinaire(mot2);
           int i = 0;
        NombreBinaire q = new NombreBinaire(0);
        int n = 0;
        while (!r.estInferieurA(b)){
        n = abs(r.getTaille()-b.getTaille());
        NombreBinaire b2 = new NombreBinaire(b);
        b2.decalage(n);
        if (r.estInferieurA(b2)){
           b2 = new NombreBinaire(b);
            b2.decalage(n-1);
            n = n-1;
        }
        r=r.soustraction(b2);
        NombreBinaire d = new NombreBinaire((int) Math.pow(2, n));
        q =q.addition(d);
        }
        
        return r;
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
         NombreBinaire sauv = new NombreBinaire(1);
        NombreBinaire base = this;
        NombreBinaire d = new NombreBinaire(exposant);
        
        while((!d.estInferieurA(new NombreBinaire(0)))&&(!d.estEgal(new NombreBinaire(0)))){
            if(!d.estPair()){
                sauv = sauv.multiplication(base).modulo(m);
            }
            d = d.decalageDroit(1);
                 
            base = base.multiplication(base).modulo(m);

        }
        return sauv;
     }
     
     //DEFI 13 - Calcul le PGCD de this et mot2
     public NombreBinaire PGCD(NombreBinaire mot2) {
         
         // Initialisation
         NombreBinaire a = new NombreBinaire(this);
         NombreBinaire b = new NombreBinaire(mot2);
         
         // Si a < b on échange a et b
         if(a.estInferieurA(b)){
            NombreBinaire tmp = new NombreBinaire(a);
            a = b;
            b = tmp;
         }
         
         // Tant que b n'est pas nul, on remplace b par a%b et a par b
         while(!b.estEgal(new NombreBinaire(0))){
             NombreBinaire previousB = new NombreBinaire(b);
             b = a.modulo(b);
             a = previousB;;
         }
         
        // Quand b est nul, on renvoie a
        return a;
     }
     
    //DEFI 14 - renvoie un nombre aléatoire entre min (inclu) et max (non inclu)
    public static NombreBinaire random(NombreBinaire min,NombreBinaire max) {

        //Différence entre le nombre max et le nombre min
            NombreBinaire diff = max.soustraction(min);
            
            //Nombre aléatoire à ajouter, entre 0 et diff
            NombreBinaire valeurAjout = NombreBinaire.randomAvecTailleMax(diff.getTaille());
            while(!valeurAjout.estInferieurA(diff)){
                valeurAjout = NombreBinaire.randomAvecTailleMax(diff.getTaille());
            }
            
            
            NombreBinaire nbAEnvoyer = min.addition(valeurAjout);
        return nbAEnvoyer;
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
