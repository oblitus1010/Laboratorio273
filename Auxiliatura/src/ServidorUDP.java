
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.StringTokenizer;
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
 **/
public class ServidorUDP {
    public static void main(String[] args){
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        //Creamos el contenedor del mensaje
        byte[] buffer;
        try {
            //Creamos el socket y se asignamos un puerto
            DatagramSocket socketUDP = new DatagramSocket(8888);
            System.out.println("Iniciando el servidor...");
            //Aqui viene lo chido
            while (true) {
                buffer = new byte[2048];
                //Creamos Paquete (peticion) que el cliente enviará, este paquete contiene la direccion y el puerto por el cual el cliente nos esta haciendo la pticion
                //A este paquete le asignamos un limite dependiente del buffer
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                //Recibimos la peticion del cliente
                //En este caso solo sera 1 peticion por cliente ya que en el enunciado no dice que sean varias
                socketUDP.receive(peticion);
                System.out.println("Cliente conectado...");
                //El mensaje esta en un array de bytes asi que lo convertimos a string
                String mensaje = new String(peticion.getData());
                //Imprimimos el mensaje que nos haya dado el cliente
                System.out.println("El cliente dice:");
                System.out.println(mensaje);
                //Guardamos en variables el puerto y la direccion del cliente
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                //Al recibir la peticion naturalmente debemos de enviar una respuesta por parte del servidor
                //Pero antes creamos el mensaje de respueta que queremos enviar
                String msgRespuesta = Integer.toString(Contar_Palabras(mensaje));
                System.out.println("Enviando respuesta al cliente...:"+msgRespuesta);
                //Pero el msgRespuesta esta en String, lo convertimos a un array de bytes de manera que sea sencillo enviarlo
                //Aprovechando el Buffer que creamos anteriormete
                buffer = msgRespuesta.getBytes();
                //Creamos el DatagramPacket que contiene la respuesta, ademas de la direccion y puerto del cliente.
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                //Enviamos la respuesta
                socketUDP.send(respuesta);
                //El cliente cierra la conexion pero imprimimos lo siguiente
                System.out.println("Cerrando conexion...");
                
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int Contar_Palabras(String cadena){
        StringTokenizer palabra = new StringTokenizer(cadena);
        return palabra.countTokens();
    }
}
