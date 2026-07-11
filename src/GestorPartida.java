/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarie
 */
public class GestorPartida {

    private int rondaActual;
    private static final int maxRondas = 3;
    
    private Jugador jugador1;
    private Jugador jugador2;

    public GestorPartida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.rondaActual = 1;
    }

    //Verifica quien gana la ronda
    public void verificarGanadorRonda() {

        if (jugador1.getPuntaje() > jugador2.getPuntaje()) {
            jugador1.ganarRonda();

        } else if (jugador2.getPuntaje() > jugador1.getPuntaje()) {
            jugador2.ganarRonda();
        }
    }
    
    //Inicia una nueva ronda 
    public void iniciarNuevaRonda() {

        jugador1.reiniciarPuntaje();
        jugador2.reiniciarPuntaje();

        rondaActual++;
    }

    // Dice si todavía quedan rondas
    public boolean quedanMasRondas() {
        return rondaActual < maxRondas;
    }
    
    // Verifica si la partida terminó
    public boolean partidaTerminada() {
        return rondaActual >= maxRondas;
    }
    
    // Obtiene el ganador de la mayoria de rondas
    public Jugador obtenerGanadorFinal() {

        if (jugador1.getRondasGanadas() > jugador2.getRondasGanadas()) {
            return jugador1;

        } else if (jugador2.getRondasGanadas() > jugador1.getRondasGanadas()) {
            return jugador2;
        }
        return null; // Sería empate
    }
   
    // Reinicia toda la partida
    public void reiniciarPartida() {

        rondaActual = 1;

        jugador1.reiniciarJugador();
        jugador2.reiniciarJugador();
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public static int getMaxRondas() {
        return maxRondas;
    }
}

    

