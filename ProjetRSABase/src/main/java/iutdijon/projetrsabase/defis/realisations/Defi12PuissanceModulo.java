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
 * @author er642070
 */
public class Defi12PuissanceModulo extends Defi{
    

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        String messageServeur = net.receiveMessage();
        NombreBinaire nb1;
        NombreBinaire nb2;
        NombreBinaire nb3;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            nb1 = new NombreBinaire(net.receiveMessage());
            nb2 = new NombreBinaire(net.receiveMessage());
            nb3 = new NombreBinaire(net.receiveMessage());
            String res = nb1.puissanceModulo(nb2, nb3).toString();
            net.sendMessage(res);
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
}
