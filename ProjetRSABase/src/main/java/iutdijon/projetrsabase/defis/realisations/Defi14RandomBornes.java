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
public class Defi14RandomBornes extends Defi {

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        
        //Annonce du défi
        String messageServeur = net.receiveMessage();
        
        //reçoit 1er nombre binaire
        String nombreMin = net.receiveMessage();
        NombreBinaire nb_nombreMin = new NombreBinaire(nombreMin);
        
        //reçoit 2ème nombre binaire
        String nombreMax = net.receiveMessage();
        NombreBinaire nb_nombreMax = new NombreBinaire(nombreMax);
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            
            net.sendMessage(NombreBinaire.random(nb_nombreMin, nb_nombreMax).toString());
            
            //verif si ok ou non
            net.receiveMessage();
            
            //recoit 1er nombre binaire suivant
            String premierNombre = net.receiveMessage();
            nb_nombreMin = new NombreBinaire(premierNombre);
            
            //recoit 2eme nombre binaire suivant
            String deuxiemeNombre = net.receiveMessage();
            nb_nombreMax = new NombreBinaire(deuxiemeNombre);
            
        }
        net.end();
    }
    
}
