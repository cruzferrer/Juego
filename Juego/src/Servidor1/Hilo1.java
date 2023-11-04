/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author elliotfrias
 */
public class Hilo1 implements Runnable {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private String personajeElegido;
    private String personaje;
    private int intentos;

    public Hilo1(Socket soc, LinkedList users, int intent, String personajeEl, String personajeN) {
        socket = soc;
        usuarios = users;
        personajeElegido = personajeEl;
        intentos = intent;
        personaje = personajeN;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String recibidos = in.readUTF();
                String recibido[] = recibidos.split(";");

                boolean ganador = gano(personajeElegido, personaje);
                
                

                for (Socket usuario : usuarios) {
                    out = new DataOutputStream(usuario.getOutputStream());
                    out.writeUTF(String.valueOf(intentos));
                }
            }
        } catch (Exception e) {

            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i) == socket) {
                    usuarios.remove(i);
                    break;
                }
            }
        }
    }

    public boolean gano(String personajeElegido, String personaje) {
        if (personaje.equalsIgnoreCase(personajeElegido)) {
            return true;
        }
        
        return false;
    }
}
