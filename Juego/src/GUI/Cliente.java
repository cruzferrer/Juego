package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;

public class Cliente {

    public Cliente(String nom, JTextArea chatTextArea) {
    }

    

    public void iniciarCliente() {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            System.out.println(input.readObject());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                System.out.print("Ingrese un mensaje: ");
                String mensaje = reader.readLine();
                output.writeObject(mensaje);
                output.flush();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void enviarMensaje(String mensaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
