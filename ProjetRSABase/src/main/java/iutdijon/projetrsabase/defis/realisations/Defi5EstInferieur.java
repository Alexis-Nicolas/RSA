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
 * 2eme défi: est inférieur
 * @author Mathis Poncet
 */
public class Defi5EstInferieur extends Defi
{

    @Override
    public void executer() throws IOException 
    {
        Network net = new Network();
        //premier message
        String messageServeur = net.receiveMessage();
        NombreBinaire nombreBit1;
        NombreBinaire nombreBit2;
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué !"))
        {
            //reçoit les 2 premiers nombres
            String nombreServeur1 = net.receiveMessage();
            String nombreServeur2 = net.receiveMessage();
            nombreBit1 = new NombreBinaire(nombreServeur1);
            nombreBit2 = new NombreBinaire(nombreServeur2);
            //envoie si le premier nombre est inférieur au 2ème
            boolean estInferieur = nombreBit1.estInferieurA(nombreBit2);
            //envoie message
            net.sendMessage(String.valueOf(estInferieur));
            //verif si ok ou non
            messageServeur = net.receiveMessage();
        }
        net.end();    
    }
    
}
