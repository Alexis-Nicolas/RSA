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
        String s = net.receiveMessage();
        System.out.println(s);
        int i;
        s = net.receiveMessage();
        while(s!="Défi validé"||s!="”Défi échoué !"){
            System.out.println(s);
            i = Integer.parseInt(s)+1;
            System.out.println(i);
            net.sendMessage(Integer.toString(i));
            s = net.receiveMessage();
            System.out.println(s);
            s = net.receiveMessage();
        }
        net.end();
    }
    
}
