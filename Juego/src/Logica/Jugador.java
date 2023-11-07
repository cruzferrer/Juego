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
class Jugador extends Thread {

    private Servidor1 conn;
    private Juego juego;
    private String jugador;

    public Jugador(String jugador, Juego juego) {
        this.jugador = jugador;
        this.juego = juego;
    }

    @Override
    public void run() {
        while (!juego.isTerminado()) {
        }
    }
}
