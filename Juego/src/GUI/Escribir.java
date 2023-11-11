package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class Escribir extends Thread {

    private Socket socket;
    private String name;

    private Inicios ac= null;

    public Escribir(Socket socket) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }
    
 public void enviarMensaje(String mensaje) {
        try {
            DataOutputStream flujoDOS = new DataOutputStream(socket.getOutputStream());
            flujoDOS.writeUTF(mensaje);
            flujoDOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public void run() {
        try {
            boolean terminar = false;
            while (!terminar) {
                OutputStream os = socket.getOutputStream();
                DataOutputStream flujoDOS = new DataOutputStream(os);

            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
