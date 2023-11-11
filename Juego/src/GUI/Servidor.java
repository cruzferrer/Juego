
package GUI;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    ArrayList<Socket> listaCliente = new ArrayList();
    int clientesConectados = 0;
    private Socket Ganador = null;
    private int Ganadores = 0;
    ServerSocket ss;
    
    
    public Servidor(){
    
}

   

    public Servidor(int b) {
        try {
            ss = new ServerSocket(5000);

            while (true) {
                System.out.println("Esperando jugadores....");
                Socket cliente = ss.accept();
                System.out.println("Conexión exitosa");
                listaCliente.add(cliente);
                clientesConectados++;

                if (clientesConectados == 2) {
                    enviarMensajeAClientes("Comenzar");
                }

                
                
                
                
                
                
                
                
                
                
                
                Inicios acc = new Inicios(listaCliente, cliente,this);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClienteGanador() {
        return Ganador;
    }

    public void setClienteGanador(Socket cliente) {
        Ganador = cliente;
    }

    public void enviarMensajeAGanador(String mensaje) {
        try {
            if (Ganador != null) {
                OutputStream os = Ganador.getOutputStream();
                DataOutputStream flujoDOS = new DataOutputStream(os);
                flujoDOS.writeUTF(mensaje);
                flujoDOS.flush();
            }
            
            
            
            
            
            
            
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarMensajeAClientes(String mensaje) {
        for (Socket cliente : listaCliente) {
            Inicios.enviarMensajeACliente(cliente, mensaje);
        }
    }
    public void detenerServidor() {
        try {
            
            
            
            if (ss != null && !ss.isClosed()) {
                ss.close();
            }

            System.exit(0); // Sale del programa
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Socket> getListaClientes() {
    return listaCliente;
}



    public static void main(String[] args) {
        new Servidor(23);
    }
}
