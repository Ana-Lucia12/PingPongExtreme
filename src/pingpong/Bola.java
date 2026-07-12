/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

/**
 *
 * @author Usuario
 */
public class Bola implements Runnable {
    
    private int x;
    private int y;
    
    private int diametro;
    
    private int velocidadX;
    private int velocidadY;
    
    private TipoBola tipo;
    
    private volatile boolean activa;
    private boolean fantasmaDisponible;
    
    private Paleta paletaIzquierda;
    private Paleta paletaDerecha;
    
    private Jugador jugador1;
    private Jugador jugador2;
    
    private ControlPausa controlPausa;

    
    public Bola(
            int x,
            int y,
            int velocidad,
            TipoBola tipo,
            Paleta paletaIzquierda,
            Paleta paletaDerecha,
            Jugador jugador1,
            Jugador jugador2,
            ControlPausa controlPausa) {
        
        this.x = x;
        this.y = y;
        this.diametro = 18;
        
        this.velocidadX = velocidad;
        this.velocidadY = velocidad;
        
        this.tipo = tipo;
              
        this.paletaIzquierda = paletaIzquierda;
        this.paletaDerecha = paletaDerecha;
        
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        
        this.controlPausa = controlPausa;
        
        this.activa = true;
        this.fantasmaDisponible = true;
    }
    
    private void mover() {
        x += velocidadX;
        y += velocidadY;
    }
    
    private void verificarReboteVertical(int altoPanel) {
        if (y <= 0) {
            y = 0;
            velocidadY = -velocidadY;
        }
        
        if (y + diametro >= altoPanel) {
            y = altoPanel - diametro;
            velocidadY = -velocidadY;
        }
    }
        
    @Override
    public void run() {
        
       while (activa) {
           
           controlPausa.esperarSiEstaPausado();
           
           if (!activa) { // evita que la bola haga otro movimiento si fue detenida mientras estaba pausada
               break;
           }
           
           mover();
           verificarReboteVertical(330);
           
           try {
               Thread.sleep(20);
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               activa = false;
           }
       }
    }
    
    public void detener() {
        activa = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiametro() {
        return diametro;
    }

    public TipoBola getTipo() {
        return tipo;
    }

    public boolean isActiva() {
        return activa;
    }
    
    
}


