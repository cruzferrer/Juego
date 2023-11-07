/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.Random;

/**
 *
 * @author elliotfrias
 */
public class LogicaJuego {
    public int escogerTurno (int turno) {
        Random rand = new Random();
        int newTurno = 0;
                
        if (turno == 0) {
            newTurno = rand.nextInt(2) + 1;
        } else {
            if (turno == 1) {
                newTurno = 2;
            } else {
                newTurno = 1;
            }
        }
        
        return newTurno;
    }
    
    public boolean ganarPerder (String personaje1, String personaje2) {
        if (personaje1.equalsIgnoreCase(personaje2)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean intentos (int intentos) {
        if (intentos > 3) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean mensajes (int numMensajes) {
        if (numMensajes > 5) {
            return false;
        } else {
            return true;
        }
    }
}
