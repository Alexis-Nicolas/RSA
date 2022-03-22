/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import iutdijon.projetrsabase.rsa.RabinMiller;
import java.io.IOException;

/**
 *
 * @author travail
 */
public class Defi23NombrePremier extends Defi{

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        //premier message
        String messageServeur = net.receiveMessage();
        
        NombreBinaire nombreBit2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué !"))
        {
            String nombreServeur2 = net.receiveMessage();
           
            nombreBit2 = new NombreBinaire(nombreServeur2);
            
            // Cherche le premier nombre binaire premier
             NombreBinaire resultat = RabinMiller.nombrePremier(nombreBit2);
             
            // Envoie la réponse
            net.sendMessage(resultat.toString());
            
            // Vérifie si c'est juste
            messageServeur = net.receiveMessage();
        }
        net.end(); 
    }
    
}
