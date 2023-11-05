/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

/**
 *
 * @author elliotfrias
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente1  {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    

    public Cliente1(String servidor, int puerto) throws IOException {
        socket = new Socket(servidor, puerto);
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);
       
    }

    public String recibirMensaje() throws IOException {
        return entrada.readLine();
    }

    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }

    
}

