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
public class Defi11RandomTailleFixee extends Defi {
    
    @Override
    public void executer() throws IOException {
        Network net = new Network();
        
        //Annonce du défi
        String messageServeur = net.receiveMessage();
        Integer entierRecu;
        
        //reçoit 1 er entier
        messageServeur = net.receiveMessage();
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            entierRecu = Integer.parseInt(messageServeur);
            
            net.sendMessage(NombreBinaire.randomAvecTailleMax(entierRecu).toString());
            
            //verif si ok ou non
            net.receiveMessage();
            
            //recoit entier suivant
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
