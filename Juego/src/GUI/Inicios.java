/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GUI.Servidor.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author cruzr
 */
public class Inicios extends Thread{
    Socket socket;
    String mensaje;
    ArrayList<Socket> listaCliente;
    int cliente;
    private Servidor servidor;
    
    Inicios(ArrayList<Socket> lista, Socket socket,Servidor servidor) {
        this.listaCliente = lista;
        this.socket = socket;
        this.servidor = servidor; 
        start();
    }
    public Inicios(){
        
    }

    public void run() {
        while (true) {
            try {
                InputStream is = socket.getInputStream();
                DataInputStream flujo = new DataInputStream(is);
                mensaje = flujo.readUTF();
                System.out.println("MESNAJE EN ACCESOS  " + mensaje);
                
                for (Socket cliente : listaCliente) {
                enviarMensajeACliente(cliente,mensaje);
                }
                
                
                if (mensaje.equals("Partida finalizada")) {
                    for (Socket cliente : listaCliente) {
                        enviarMensajeACliente(cliente,"close");
                    }
                    servidor.detenerServidor(); 
                }

                if (mensaje.equals("Comenzar")) {
                    Inicios.enviarMensajeACliente(socket, "Comenzar");
                } else {
                    for (int cont = 0; cont < listaCliente.size(); cont++) {
                        OutputStream os = listaCliente.get(cont).getOutputStream();
                        DataOutputStream flujoDOS = new DataOutputStream(os);
                        flujoDOS.writeUTF(mensaje);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error de comunicación: " + e);
            }
        }
    }

    public static void enviarMensajeACliente(Socket cliente, String mensaje) {
        try {
            OutputStream os = cliente.getOutputStream();
            DataOutputStream flujoDOS = new DataOutputStream(os);
            flujoDOS.writeUTF(mensaje);
            System.out.println("mensaje enviado con exito ");
        } catch (Exception e) {
            System.out.println("Error al enviar mensaje al cliente: " + e);
        }
    }
    
}
