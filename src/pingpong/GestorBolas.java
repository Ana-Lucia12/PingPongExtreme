/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.util.concurrent.CopyOnWriteArrayList;

public class GestorBolas {
    
    private final CopyOnWriteArrayList<Bola> bolas;
    
    public GestorBolas() {
        bolas = new CopyOnWriteArrayList<>();
    }
    
    public void agregarBola(Bola bola) {
        
        if (bola == null) {
            return;
        }
        
        bolas.add(bola);
        
        // hilo para cada bola
        Thread hiloBola = new Thread(bola);
        hiloBola.start();
    }
    
    // eliminar una bola
    public void eliminarBola (Bola bola) {
        if (bola == null) {
            return;
        }
        
        bola.detener();
        bolas.remove(bola);
    }
    
    // detener todas las bolas
    public void detenerTodas() {
        for (Bola bola : bolas) {
            bola.detener();
        }
        
        bolas.clear();
    }
    
    // obtener las bolas
    public CopyOnWriteArrayList<Bola> getBolas() {
        return bolas; // permite que el panel recorra las bolas para mostrarlas en pantalla 
    }
    
    //obtener cantidad
    public int cantidadBolasActivas() {
        return bolas.size();
    }
    
    public void eliminarBolasInactivas() {
        
        for (Bola bola : bolas) {
            
            if (!bola.isActiva()) {
                bolas.remove(bola);
            }
        }
    }
}
