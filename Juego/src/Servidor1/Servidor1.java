/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elliotfrias
 */


public class Servidor1 {
    private ServerSocket serverSocket;
    private List<Socket> clientes = new ArrayList<>();

    public Servidor1(int puerto, int maxConexiones) throws IOException {
        serverSocket = new ServerSocket(puerto);
        serverSocket.setSoTimeout(10000); // Establece un tiempo de espera de 10 segundos para las conexiones

        while (clientes.size() < maxConexiones) {
            try {
                Socket clienteSocket = serverSocket.accept();
                clientes.add(clienteSocket);
                System.out.println("Nuevo cliente conectado. Total de clientes: " + clientes.size());

            } catch (IOException e) {
                // Maneja las excepciones si hay problemas con las conexiones
                e.printStackTrace();
            }
        }
    }
}


