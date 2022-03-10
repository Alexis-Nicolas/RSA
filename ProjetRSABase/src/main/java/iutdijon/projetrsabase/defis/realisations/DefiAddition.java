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
 * @author ewanr
 */
public class DefiAddition extends Defi{

    @Override
    public void executer() throws IOException {
         Network net = new Network();
        String messageServeur = net.receiveMessage();
        System.out.println(messageServeur);
        NombreBinaire nb1;
        NombreBinaire nb2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            nb1 = new NombreBinaire(net.receiveMessage());
            System.out.println("Premier nombre binaire :"+nb1);
            System.out.println("-----");
            nb2 = new NombreBinaire(net.receiveMessage());
            System.out.println("Deuxième nombre binaire :"+nb2);
            System.out.println("-----");
            NombreBinaire nbRes = nb1.addition(nb2);
            System.out.println(nbRes);
            net.sendMessage(nbRes.toString());
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
