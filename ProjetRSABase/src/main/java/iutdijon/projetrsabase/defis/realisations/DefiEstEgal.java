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
public class DefiEstEgal extends Defi{

    @Override
    public void executer() throws IOException {
        
        // Initialisation
        Network net = new Network();
        String messageServeur1 = net.receiveMessage();
        String messageServeur2;
        NombreBinaire premierNb;
        NombreBinaire secondNb;
        Boolean res = false;
        
        // Reçoit les nombres
        messageServeur1 = net.receiveMessage();
        messageServeur2 = net.receiveMessage();
        
        while(!messageServeur1.equals("Défi validé")|| !messageServeur1.equals("Défi échoué!"))
        {
            // Traduit les messages en nombres binaires
            premierNb = new NombreBinaire(messageServeur1);
            secondNb = new NombreBinaire(messageServeur2);
            
            // Récupère le résultat et répond au serveur
            res = premierNb.estEgal(secondNb);
            net.sendMessage(Boolean.toString(res));
            
            // Reçoit le OK ou le NOK
            messageServeur1 = net.receiveMessage();
            
            // Reçoit la fin ou une autre paire de nombres binaires
            messageServeur1 = net.receiveMessage();
            
            // Vérifie si le message envoyé est la fin ou une nouvelle paire de nombres binaires
            if(!messageServeur1.equals("Défi validé")|| !messageServeur1.equals("Défi échoué!")){
                messageServeur2 = net.receiveMessage();
            }
        }
        net.end();
    }
    
}
