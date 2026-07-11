
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarie
 */
public class Temporizador {
    
    private Timer timer;
    private int tiempo;
    
    public Temporizador(ActionListener accion) {
        
        tiempo = 60;

        timer = new Timer(1000, e -> {

            tiempo--;

            // Actualiza la interfaz cada segundo
            if (accion != null) {
                accion.actionPerformed(
                    new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "ACTUALIZAR"));
            }

            // Cuando termina el tiempo
            if (tiempo <= 0) {

                timer.stop();

                if (accion != null) {
                    accion.actionPerformed(
                        new ActionEvent(
                            this,
                            ActionEvent.ACTION_PERFORMED,
                            "FIN"));
                }
            }
            
        });
    }   

    //Iniciar el temporizador
    public void iniciar() { 
       if(!timer.isRunning()){
            timer.start();
        } 
    }

    //Pausar el temporizador
    public void pausar() { 
        timer.stop();
    }

    //Reiniciar
    public void reiniciar() {
        timer.stop();
        tiempo = 60;
    }
    
    // Reiniciar y iniciar de nuevo
    public void reiniciarYIniciar() {
        reiniciar();
        iniciar();
    }
    
    // Saber si está activado
    public boolean estaActivo() {
        return timer.isRunning();
    }
    
    //Tiempo que falta
    public int getTiempo(){
        return tiempo;
    }
}
