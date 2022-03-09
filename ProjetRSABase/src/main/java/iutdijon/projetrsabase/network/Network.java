/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iutdijon.projetrsabase.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        this.socket = new Socket("127.0.0.1",1234);
        this.bufr = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())),true);
    }
    
    /**
     * Envoi d'un message
     */
    public void sendMessage(String message) throws IOException {
       
       this.pw.print(message);
    
    }
        
    /**
     * Réception d'un message
     */
    public String receiveMessage() throws IOException {
        
        return this.bufr.readLine();
    }
    
    /**
     * Fin de la connexion
     */
    public void end() throws IOException {
        this.pw.print("FIN");
    }
}
