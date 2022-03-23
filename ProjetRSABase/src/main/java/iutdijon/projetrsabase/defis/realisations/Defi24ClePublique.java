/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.GenerateurDeClesRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import java.io.IOException;

/**
 *
 * @author travail
 */
public class Defi24ClePublique extends Defi {


    @Override
    public void executer() throws IOException {
        Network net = new Network();
        //premier message du serveur
        String messageServeur = net.receiveMessage();

        while(!messageServeur.equals("Defi valide") || !messageServeur.equals("Defi echoue !"))
        {
            //on génère la clé publique
            GenerateurDeClesRSA.genererClePublique();
            
            //on envoie les réponses
            net.sendMessage(GenerateurDeClesRSA.getP().toString());
            net.sendMessage(GenerateurDeClesRSA.getQ().toString());
            net.sendMessage(GenerateurDeClesRSA.getN().toString());
            net.sendMessage(GenerateurDeClesRSA.getPhi().toString());
            net.sendMessage(GenerateurDeClesRSA.getE().toString());
  
            //reçoit ok ou non, vérifie que le défie a été
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
}
