/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarie
 */
public class Jugador {
    
    // Variables
    private String nombre;
    private int puntaje;
    private int rondasGanadas;
    
    // Constructor
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.rondasGanadas = 0;
    }
    
    // Setters y getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }
    
    
    //Métodos 
    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public void ganarRonda() {
        this.rondasGanadas++;
    }    

    public void reiniciarPuntaje() {
        puntaje = 0;
    } 
    
    public void reiniciarJugador() {
        puntaje = 0;
        rondasGanadas = 0;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
