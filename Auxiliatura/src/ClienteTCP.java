
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oblitus
 */
public class ClienteTCP {
    static Scanner lee = new Scanner(System.in);
    public static void main(String[] args){
        //De la misma manera que en el servidor creamos variables
        DataInputStream in;
        DataOutputStream out;
        //Aqui creamos lo chido
        try {
            //Creamos un socket como servidor
            Socket servidorTCP = new Socket("127.0.0.1", 8888);
            // Iniciamos in y out
            in = new DataInputStream(servidorTCP.getInputStream());
            out = new DataOutputStream(servidorTCP.getOutputStream());
            
            while(true){
                Desplegar_Menu();
                String mensaje = lee.next();
                //Enviamos un mensaje al servidor
                System.out.println("Enviando mensaje...");
                out.writeUTF(mensaje);
                //MOstramos la respuesta del servidor
                System.out.println("El servidor dice...: "+in.readUTF());
                if(mensaje.equals("4"))break;
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void Desplegar_Menu(){
        System.out.println("****************MY MENU******************");
        System.out.println("1.-Opcion 1" );
        System.out.println("2.-Opcion 2");
        System.out.println("3.-Opcion 3");
        System.out.println("4.-Salir");
        System.out.print("Igrese una opcion...:");
    }
}