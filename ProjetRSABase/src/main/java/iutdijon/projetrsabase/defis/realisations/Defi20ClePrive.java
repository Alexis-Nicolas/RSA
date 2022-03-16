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
 * @author Mathis Poncet
 */
public class Defi20ClePrive extends Defi
{

    @Override
    public void executer() throws IOException 
    {
        Network net = new Network();
        //premier message du serveur
        String messageServeur = net.receiveMessage();
        NombreBinaire nombreP;
        NombreBinaire nombreQ;
        NombreBinaire nombreE;
        NombreBinaire clePriveGenere;
        while(!messageServeur.equals("Defi valide") || !messageServeur.equals("Defi echoue !"))
        {
            //reçoit le premier nombre du triplet
            String messageWithP = net.receiveMessage();
            String messageWithQ = net.receiveMessage();
            String messageWithE = net.receiveMessage();
            nombreP = new NombreBinaire(messageWithP);
            nombreQ = new NombreBinaire(messageWithQ);
            nombreE = new NombreBinaire(messageWithE);
            //on génère la clé privée
            clePriveGenere = 
                    GenerateurDeClesRSA.genererClePrive(nombreP, nombreQ, nombreE);
            //on envoie la clé privée générée
            net.sendMessage(clePriveGenere.toString());
            //reçoit ok ou non, vérifie que le défie a été
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
