/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.util.Random;

public class GeneradorBolas implements Runnable {
    
    private final GestorBolas gestorBolas;
    private final ConfiguracionDificultad dificultad;
    private final Paleta paletaIzquierda;
    private final Paleta paletaDerecha;
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final ControlPausa controlPausa;
   

    private final Random aleatorio;

    private volatile boolean activo;
    
    
    public GeneradorBolas(
            GestorBolas gestorBolas,
            ConfiguracionDificultad dificultad,
            Paleta paletaIzquierda,
            Paleta paletaDerecha,
            Jugador jugador1,
            Jugador jugador2,
            ControlPausa controlPausa) {

        this.gestorBolas = gestorBolas;
        this.dificultad = dificultad;
        this.paletaIzquierda = paletaIzquierda;
        this.paletaDerecha = paletaDerecha;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.controlPausa = controlPausa;

        this.aleatorio = new Random();
        this.activo = true;
    }
    
    // generar tipo de bola especial aleatorio
    
    private TipoBola generarTipoAleatorio() {
        
        int numero = aleatorio.nextInt(100);
        
        if (numero >= dificultad.getProbabilidadEspecial()) {
            return TipoBola.NORMAL;
        }
        
        TipoBola[] especiales = {
            TipoBola.NEGATIVA,
            TipoBola.BONUS,
            TipoBola.RAPIDA,
            TipoBola.FANTASMA,
            TipoBola.CONGELANTE
        };
        
        int posicion = aleatorio.nextInt(especiales.length);
        
        return especiales[posicion];
    }
    
    // crear una bola
    private Bola crearBola() {
        
        int xInicial = 335;
        int yInicial = 165;
        
        int velocidad = dificultad.getVelocidadBola();
        
        if (aleatorio.nextBoolean()) {
            velocidad = -velocidad;
        }
        
        TipoBola tipo = generarTipoAleatorio();
        
        return new Bola(
                xInicial,
                yInicial,
                velocidad,
                tipo,
                paletaIzquierda,
                paletaDerecha,
                jugador1,
                jugador2,
                controlPausa
        );
    }
    
    @Override
    public void run() {
        
        while (activo) {
            
            controlPausa.esperarSiEstaPausado();
            
            if (!activo) {
                break;
            }
            
            if (gestorBolas.cantidadBolasActivas() < dificultad.getMaximoBolas()) {
                
                Bola bola = crearBola();
                gestorBolas.agregarBola(bola);
            }
            
            try {
                Thread.sleep(
                        dificultad.getFrecuenciaAparicion()
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                activo = false;
            }
        }
    }
    
    public void detener() {
        activo = false;
    }
    
}
