/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor1 {

    private static final int PORT = 8080;
    private static List<Socket> clientes = new ArrayList<>();

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor WebSocket de Adivina Quién escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo jugador conectado desde " + clientSocket.getInetAddress());
                clientes.add(clientSocket);

                // Manejar la conexión en un hilo separado
                new AdivinaQuienWebSocketHandler(clientSocket, clientes).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AdivinaQuienWebSocketHandler extends Thread {

    private Socket socket;
    private List<Socket> clientes;
    private PrintWriter out;
    private BufferedReader in;

    public AdivinaQuienWebSocketHandler(Socket socket, List<Socket> clientes) {
        this.socket = socket;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("¡Bienvenido al juego de Adivina Quién!");

            String pregunta;
            while ((pregunta = in.readLine()) != null) {
                System.out.println("Pregunta recibida: " + pregunta);

                // Lógica del juego: Procesar la pregunta y enviar una respuesta
                String respuesta = procesarPregunta(pregunta);

                // Enviar la respuesta al jugador que hizo la pregunta
                out.println("Respuesta: " + respuesta);

                // Envía la respuesta a todos los jugadores
                broadcast("Turno " + Thread.currentThread().getId() + ": " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                clientes.remove(socket); // Eliminar el cliente desconectado de la lista
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String procesarPregunta(String pregunta) {
        // Implementa la lógica de procesar la pregunta aquí
        // Por ejemplo, verifica si la pregunta revela la identidad del personaje

        // En este ejemplo, simplemente se devuelve una respuesta fija
        return "No tienes suficiente información para adivinar aún. Sigue preguntando.";
    }

    // Método para enviar un mensaje a todos los jugadores
    private void broadcast(String mensaje) {
        for (Socket cliente : clientes) {
            try {
                PrintWriter clienteOut = new PrintWriter(cliente.getOutputStream(), true);
                clienteOut.println(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
