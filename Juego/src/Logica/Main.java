/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Servidor1.Servidor1;

/**
 *
 * @author elliotfrias
 */
public class Main {

    public static void main(String[] args) {
        Juego juego = new Juego();
        Servidor1 server = new Servidor1();
        
        server.iniciar();
        
        Jugador jugador1 = new Jugador("Jugador 1", juego);
        Jugador jugador2 = new Jugador("Jugador 2", juego);

        // Iniciar los hilos de los jugadores
        jugador1.start();
        jugador2.start();        
        
    }
}
