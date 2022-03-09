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
public class Defi3Decalage extends Defi {

    @Override
    public void executer() throws IOException {
        //Création du network
        Network net = new Network();
        
        //Message d'annonce
        String messageServeur = net.receiveMessage();
        
        //Reçoit le nombre binaire
        String nombre = net.receiveMessage();
        
        //reçoit le nombre de décalage
        String nbDecalage = net.receiveMessage();
        
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            
            //Création du nombre binaire
            NombreBinaire nb = new NombreBinaire(nombre);
            
            //On va instancier le nombre binaire avec un décalage
            NombreBinaire messageAEnvoyer = nb.decalage(Integer.parseInt(nbDecalage));
            
            //On envoie le message en string
            net.sendMessage(messageAEnvoyer.toString());
            
            
            //verif si ok ou non
            messageServeur = net.receiveMessage();
            
            
            //recoit le nombre binaire suivant
            nombre = net.receiveMessage();
            
            //recoit le nombre de décalage suivant
            nbDecalage = net.receiveMessage();
        }
        net.end();
    }
    
}
