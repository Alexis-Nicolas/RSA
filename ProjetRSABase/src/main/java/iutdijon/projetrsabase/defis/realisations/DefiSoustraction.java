/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import java.io.IOException;
import iutdijon.projetrsabase.rsa.NombreBinaire;

/**
 *
 * @author alexi
 */
public class DefiSoustraction extends Defi{

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        String bit1;
        String bit2;
        String messageServeur = net.receiveMessage();
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
           bit1= messageServeur;
           bit2= net.receiveMessage();
           NombreBinaire n1 = new NombreBinaire(bit1); 
           NombreBinaire n2 = new NombreBinaire(bit2); 
           NombreBinaire n3 = n1.soustraction(n2);
           net.sendMessage(n3.toString());
           //verif si ok ou non
            messageServeur = net.receiveMessage();
            //recoit nbbinaire1 suivant
            bit1 = net.receiveMessage();
            //recoit nbbinaire2 suivant
            bit2 = net.receiveMessage();
        }
        net.end();
    }
    
}
