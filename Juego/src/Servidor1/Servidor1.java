/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor1;
    import java.io.ObjectInputStream;
import GUI.Chat;
import GUI.Chat.MensajeDTO;
import GUI.Chat.Paquete;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectInputStream;
/**
 *
 * @author elliotfrias
 */


public class Servidor1 {
    private ServerSocket serverSocket;
    private List<Socket> clientes = new ArrayList<>();
    private Chat chat;

    public Servidor1(Chat chat) {
        this.chat = chat;
    }

public void run() {
    try {
        ServerSocket serv = new ServerSocket(8080);

        System.out.println("El servidor está corriendo...");

        while (true) {
            Socket sock = serv.accept();

            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            MensajeDTO mensajeDTO = (MensajeDTO) ois.readObject();
            
            String n = mensajeDTO.getNombre();
            String ip = mensajeDTO.getIp();
            String m = mensajeDTO.getMensaje();

            // Usa el objeto chat para acceder al método actualizarTextArea
            chat.actualizarTextArea(n, ip, m);

            sock.close();
        }
    } catch (IOException | ClassNotFoundException ex) {
        ex.printStackTrace();
    }
}



     public static void main(String[] args) {
        // Crea una instancia de OtraClase
        Chat otraClase = new Chat("a");

        // Crea una instancia de Servidor1 y pasa la referencia a OtraClase
        Servidor1 servidor = new Servidor1(otraClase);

        // Inicia el servidor
        servidor.run();
    }
}


