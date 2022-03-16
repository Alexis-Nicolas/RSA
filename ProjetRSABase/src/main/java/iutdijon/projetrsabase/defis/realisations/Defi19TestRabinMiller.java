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
 * @author alexi
 */
public class Defi19TestRabinMiller extends Defi{
    @Override
    public void executer() throws IOException 
    {
        Network net = new Network();
        //premier message
        String messageServeur = net.receiveMessage();
        
        NombreBinaire nombreBit2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué !"))
        {
            
           
            String nombreServeur2 = net.receiveMessage();
           
            nombreBit2 = new NombreBinaire(nombreServeur2);
            //envoie si le premier nombre est inférieur au 2ème
             boolean test = RabinMiller.testRabinMiller(nombreBit2);
            //envoie message
            net.sendMessage(String.valueOf(test));
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();    
    }
}
