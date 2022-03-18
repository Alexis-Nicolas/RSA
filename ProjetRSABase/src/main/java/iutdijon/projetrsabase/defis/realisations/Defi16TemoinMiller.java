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
 * @author rc438799
 */
public class Defi16TemoinMiller extends Defi{

    @Override
    public void executer() throws IOException {
        Network net = new Network();
        
        //premier message
        String messageServeur = net.receiveMessage();
        NombreBinaire n;
        NombreBinaire a;
        
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué !"))
        {
            //reçoit les 2 premiers nombres
            String nombreServeur1 = net.receiveMessage();
            String nombreServeur2 = net.receiveMessage();
            n = new NombreBinaire(nombreServeur1);
            a = new NombreBinaire(nombreServeur2);
            
            //envoie si le premier nombre est inférieur au 2ème
            boolean temoin = RabinMiller.temoin(n, a);
            
            //envoie message
            net.sendMessage(Boolean.toString(temoin));
            
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();  
    }
}
