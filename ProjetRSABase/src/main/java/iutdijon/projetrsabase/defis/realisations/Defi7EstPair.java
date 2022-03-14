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
 * @author lm178867
 */
public class Defi7EstPair extends Defi {

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        //Message d'annonce
        String messageServeur = net.receiveMessage();
        
        //reçoit l'entier
        messageServeur = net.receiveMessage();
        
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            
            //Création du nombre binaire
            NombreBinaire nb = new NombreBinaire(messageServeur);
            
            System.out.println(nb);
            
            boolean messageAEnvoyer = nb.estPair();
            
            //On envoie le message true ou false
            net.sendMessage(String.valueOf(messageAEnvoyer));
            
            
            //verif si ok ou non
            messageServeur = net.receiveMessage();
            
            
            //recoit entier suivant
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
