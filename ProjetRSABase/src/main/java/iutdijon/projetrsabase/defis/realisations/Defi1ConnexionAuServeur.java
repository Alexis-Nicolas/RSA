package iutdijon.projetrsabase.defis.realisations;

import iutdijon.projetrsabase.defis.Defi;
import iutdijon.projetrsabase.network.Network;
import java.io.IOException;

/**
 * 1er défi : Connexion au serveur
 * @author Matthieu
 */
public class Defi1ConnexionAuServeur extends Defi {

    @Override
    public void executer() throws IOException{
        Network net = new Network();
        String messageServeur = net.receiveMessage();
        Integer entierRecu;
        //reçoit 1 er entier
        messageServeur = net.receiveMessage();
        while(!messageServeur.equals("Défi validé")|| !messageServeur.equals("Défi échoué!"))
        {
            entierRecu = Integer.parseInt(messageServeur)+1;
            net.sendMessage(Integer.toString(entierRecu));
            //verif si ok ou non
            messageServeur = net.receiveMessage();
            //recoit entier suivant
            messageServeur = net.receiveMessage();
        }
        net.end();
    }
    
}
