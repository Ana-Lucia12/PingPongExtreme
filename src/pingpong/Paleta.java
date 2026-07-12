
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;
/**
 *
 * @author yarie
 */
public class Paleta {
    
    // Variable
    private int x, y;
    private int velocidad;
    private static final int VELOCIDAD_NORMAL = 7;
    private static final int VELOCIDAD_REDUCIDA = 2;
    private static final int ancho = 15, alto = 80;
    
    //Constructor

    public Paleta(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidad = VELOCIDAD_NORMAL;
    }

    //Movilidad
    public void moverArriba() {
        if (y > 0) {
            y -= velocidad;
        }
    }

    public void moverAbajo(int limite) {
        if (y + alto < limite) {
            y += velocidad;
        }
    }
    
    public void reiniciar(int nuevaY) {
        y = nuevaY;
        velocidad = VELOCIDAD_NORMAL;
    }
    
    public synchronized void reducirVelocidad() {
        velocidad = VELOCIDAD_REDUCIDA;
    }
    
    public synchronized void restaurarVelocidad() {
        velocidad = VELOCIDAD_NORMAL;
    }
    
    //Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public synchronized int getVelocidad() {
        return velocidad;
    }

    //Setters

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
