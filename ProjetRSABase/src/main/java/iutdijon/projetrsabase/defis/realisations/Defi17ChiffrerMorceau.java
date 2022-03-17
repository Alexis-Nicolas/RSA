/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import iutdijon.projetrsabase.rsa.AlgorithmeRSA;
import iutdijon.projetrsabase.rsa.NombreBinaire;
import iutdijon.projetrsabase.rsa.ParametresRSA;
import java.io.IOException;

/**
 * Défi déchiffrer morceau
 * @author Mathis Poncet
 */
public class Defi17ChiffrerMorceau extends Defi
{

    @Override
    public void executer() throws IOException 
    {
        Network net = new Network();
        //premier message du serveur
        String messageServeur = net.receiveMessage();
        NombreBinaire nombreM;
        NombreBinaire nombreN;
        NombreBinaire nombreE;
        while(!messageServeur.equals("Defi valide") || !messageServeur.equals("Defi echoue !"))
        {
            //reçoit le premier nombre du triplet
            String messageWithM = net.receiveMessage();
            String messageWithN = net.receiveMessage();
            String messageWithE = net.receiveMessage();
            nombreM = new NombreBinaire(messageWithM);
            nombreN = new NombreBinaire(messageWithN);
            nombreE = new NombreBinaire(messageWithE);
            NombreBinaire morceauChiffre = 
                    AlgorithmeRSA.chiffrerMorceau(nombreM, nombreN, nombreE);
            //on envoie la clé privée générée
            net.sendMessage(morceauChiffre.toString());
            //reçoit ok ou non, vérifie que le défie a été
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
