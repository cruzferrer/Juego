/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor1;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import Servidor1.Hilo1;

/**
 *
 * @author elliotfrias
 */
public class Servidor1 {

    private final int puerto = 8080;
    private final int noConexiones = 2;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private Boolean turno = true;
    private int turnos = 1;

    public void escuchar() {
        try {
            ServerSocket servidor = new ServerSocket(puerto, noConexiones);
            System.out.println("Esperando jugadores....");
            while (true) {
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                //Se le genera un turno X o O 
                int xo = turnos % 2 == 0 ? 1 : 0;
                turnos++;
                
                
                
                Runnable run = new Hilo1(cliente, usuarios, 3);
                Thread hilo = new Thread(run);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion main para correr el servidor
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.escuchar();
    }
}
}
