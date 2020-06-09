
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
public class ServidorTCP {
    public static void main(String[] args){
        //Creamos las variables necesarias pero aun no las inciamos
        ServerSocket  servidorTCP = null;
        Socket cliente = null;
        //Estas librerias facilita en gran medida la recepcion y envio de datos
        DataInputStream in;
        DataOutputStream out;
        //Desde aqui viene lo chido
        try {
            //Iniciamos el servidor y le asignamos un puerto
            servidorTCP = new ServerSocket(8888);
            System.out.println("Servidor iniciado....");
            
            //Ponemos while de manera que acepte a n clientes
            while(true){
                //Aqui el serverSocket acepta a algun cliente
                cliente = servidorTCP.accept();
                System.out.println("Cliente conectado...");
                in = new DataInputStream(cliente.getInputStream());
                out = new DataOutputStream(cliente.getOutputStream());
                while(true){
                    //REcibimos lo que el cliente nos envia
                    String peticion = in.readUTF();
                    switch(peticion){
                        case "1":
                            System.out.println("Peticion :"+peticion+"\n"+"Enviando : papel");
                            out.writeUTF("papel");
                            break;
                        case "2":
                            System.out.println("Peticion :"+peticion+"\n"+"Enviando : piedra");
                            out.writeUTF("piedra");
                            break;
                        case "3":
                            System.out.println("Peticion :"+peticion+"\n"+"Enviando : tijera");
                            out.writeUTF("tijera");
                            break;
                        case "4":
                            System.out.println("Peticion :"+peticion+"\n"+"Cerrando conexion...");
                            out.writeUTF("Adios!");
                            cliente.close();
                            break;
                    }
                    if(peticion.equals("4")){
                        System.out.println("Cliente desconectado");
                        break;
                    }
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
