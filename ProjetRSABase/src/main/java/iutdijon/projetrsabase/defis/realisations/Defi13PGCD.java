/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author rc438799
 */
public class Defi13PGCD extends Defi{

    @Override
    public void executer() throws IOException {
        
        Network net = new Network();
        
        //premier message
        String messageServeur = net.receiveMessage();
        NombreBinaire nombreBit1;
        NombreBinaire nombreBit2;
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué !"))
        {
            //reçoit les 2 premiers nombres
            String nombreServeur1 = net.receiveMessage();
            String nombreServeur2 = net.receiveMessage();
            nombreBit1 = new NombreBinaire(nombreServeur1);
            nombreBit2 = new NombreBinaire(nombreServeur2);
            
            //envoie si le premier nombre est inférieur au 2ème
            NombreBinaire modulo = nombreBit1.PGCD(nombreBit2);
            
            //envoie message
            net.sendMessage(modulo.toString());
            
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();  
    }
    
}
