/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private final List<ObjectOutputStream> clientes = new ArrayList<>();

    public static void main(String[] args) {
        new Servidor().iniciarServidor();
    }

    public void iniciarServidor() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                clientes.add(outputStream);

                Thread hiloCliente = new Thread(new ManejarCliente(socket, outputStream));
                hiloCliente.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ManejarCliente implements Runnable {
        private final Socket clienteSocket;
        private final ObjectOutputStream outputStream;

        public ManejarCliente(Socket socket, ObjectOutputStream outputStream) {
            this.clienteSocket = socket;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream output = new ObjectOutputStream(clienteSocket.getOutputStream());
                output.writeObject("Conexión exitosa");

                BufferedReader reader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

                String mensaje;
                while ((mensaje = reader.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    enviarMensajeTodos(mensaje);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void enviarMensajeTodos(String mensaje) {
            for (ObjectOutputStream cliente : clientes) {
                try {
                    cliente.writeObject(mensaje);
                    cliente.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
