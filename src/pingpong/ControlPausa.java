/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

/**
 *
 * @author Usuario
 */
public class ControlPausa {
    private boolean pausado;
    
    public ControlPausa() {
        pausado = false;
    }
    
    public synchronized void pausar() {
        pausado = true;
    }
    
    public synchronized void reanudar() {
        pausado = false;
        notifyAll();
    }
    
    public synchronized void esperarSiEstaPausado() {
        while (pausado) { // mientras siga pausado el hilo sigue esperando
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; 
                
            }
        }
    }
    
    public synchronized boolean isPausado() {
        return pausado;
    }
            
}

