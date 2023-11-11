/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author santi
 */
public class Leer extends Thread {

    Socket socket;
    DataOutputStream flujoDOS;

    public Leer(Socket socket) {
        this.socket = socket;
        try {
            flujoDOS = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    Leer() {
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream flujoDIS = new DataInputStream(is);
            System.out.println("YO SOY EL SOCKET DE LEER");

            while (true) {
                String mensaje = flujoDIS.readUTF();
                System.out.println(mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void enviarMensaje(Socket socket, String mensaje) {
    try {
        DataOutputStream flujoDOS = new DataOutputStream(socket.getOutputStream());
        flujoDOS.writeUTF(mensaje);
        flujoDOS.flush();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public static String recibirMensaje(Socket socket) {
        try {
            InputStream is = socket.getInputStream();
            DataInputStream flujoDIS = new DataInputStream(is);
            return flujoDIS.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void detenerLectura() {
        interrupt();
    }
}
