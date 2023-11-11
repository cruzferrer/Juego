package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.DataOutputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cliente {
    private javax.swing.JButton X;
    private javax.swing.JTextField chatbox;
    private javax.swing.JTextPane iP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pan;
    private javax.swing.JPanel panChat;
    private javax.swing.JTextPane per;

    private JTextField messageField;
    private JTextArea chatArea;
    private String nombre;
    private PrintWriter out;
    private DataOutputStream Salida;
    
    public Cliente(String nom) throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {


        this.nombre = nom;
        
        
        JFrame frame = new JFrame("Chat Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(831, 510);
        
        pan = new javax.swing.JPanel();
        
        
        pan.setSize(831, 510);
        chatArea  = new JTextArea(10, 30);
        chatArea.setWrapStyleWord(true);
        chatArea.setLineWrap(true);
        chatArea.setEditable(false);

        messageField = new JTextField();
        
        
        JButton sendButton = new JButton("Enviar");

        System.out.println("todo bien");
        
        Socket skCliente = new Socket("localhost", 5000);
        System.out.println(skCliente);
        Salida = new DataOutputStream(skCliente.getOutputStream());
        while (true) {
            System.out.println("Esperando...");
            String mensaje = Leer.recibirMensaje(skCliente);

            if (mensaje.equals("Comenzar")) {

                enviarMensajeAlServidor("Hola soy " + nombre);

                Escribir hilo1 = new Escribir(skCliente);//este escribe we

                Leer hilo2 = new Leer(skCliente);// este lee we

                break;

            }
            while (true) {
                System.out.println("Esperando a que finalice la partida...");

                String mensaj = Leer.recibirMensaje(skCliente);
                System.out.println(mensaj + " AAAAAAAAAAAAAAAA");
                if (mensaj.equals("Partida finalizada")) {
                    enviarMensajeAlServidor("Partida finalizada");
                    skCliente.close();
                    break;
                }

            }
        }
        
        

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String chat = messageField.getText();
                messageField.setText("");
                
                System.out.println(chat);
               

                
                enviarMensajeAlServidor(nombre+": " + chat);
                
                 String serv = Leer.recibirMensaje(skCliente);
      
     chatArea.append("usuario: " + serv+ "\n"); // aregar el mensaje nuevo 
        
                
                
            }
        });

        
        pan.setLayout(new BorderLayout());
        
        
        pan.add(messageField, BorderLayout.CENTER);
        pan.add(sendButton, BorderLayout.EAST);
        frame.add(chatArea, BorderLayout.CENTER);
        frame.add(pan,BorderLayout.SOUTH);
        frame.setVisible(true);

        //ip 192.168.20.10
        System.out.println("esta llegando ");
    }

    private void enviarMensajeAlServidor(String mensaje) {
        try {
            Salida.writeUTF(mensaje);
            Salida.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Cliente("a");
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
