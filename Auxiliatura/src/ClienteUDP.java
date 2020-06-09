
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
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
public class ClienteUDP {
    public static void main(String[] args) throws IOException{
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        //Creamos el puerto del servidor y el contenedor del mensaje
        byte[] buffer = new byte[2048];
        try {
            //Creamos el socket y en este caso no le asignamos un puerto
            //Esto debidoa a que el cliente no necesita un puerto ya que se le asignara uno
            DatagramSocket socketUDP = new DatagramSocket();
            System.out.println("Conectando con el servidor...");
            //Creamos la direccion de red del servidor "localhost"
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            //Creamos el string del mensaje
            System.out.print("Introduzca el mensaje...: ");
            String mensaje = leer.readLine();
            //Creamos el array de bytes del mensaje
            buffer = mensaje.getBytes();
            //Creamos el DatagramPacket que lleva en la cabecera la direccion y el puerto del servidor
            DatagramPacket solicitud = new DatagramPacket(buffer, buffer.length, direccionServidor, 8888);
            System.out.println("Enviando la solicitud al servidor!");
            //Enviamos la solicitud
            socketUDP.send(solicitud);
            //limpiamos el buffer
            buffer = new byte[2048];
            //Esperamos la respuesta del servidor y la recibimos en un DatagramPacket
            DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
            //Recibimos la respuesta del servidor
            socketUDP.receive(respuestaServidor);
            //Convertimos a string la respuesta
            String msgRespuesta = new String(respuestaServidor.getData());
            //Imprimimos la respuesta
            System.out.println("El numero de palabras en la oración es : "+msgRespuesta);
            socketUDP.close();
                        
        } catch (SocketException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
