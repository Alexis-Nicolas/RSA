/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client de connexion TCP
 * @author Matthieu
 */
public class Network {
    private Socket socket; 
    private PrintWriter pw;
    private BufferedReader bufr;
    
    /**
     * Constructeur
     * @throws IOException 
     */
    public Network() throws IOException {
        
    }
    
    /**
     * Envoi d'un message
     */
    public void sendMessage(String message) {
       
    }
        
    /**
     * Réception d'un message
     */
    public String receiveMessage() throws IOException {
        return null;
    }
    
    /**
     * Fin de la connexion
     */
    public void end() throws IOException {
        
    }
}
