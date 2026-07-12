/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.Rectangle;

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
        
        if (tipo == TipoBola.RAPIDA) {
            this.velocidadX *= 2;
            this.velocidadY *= 2;
        }
        
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
           verificarChoquePaletas();
           verificarSalidaLateral(670);
           
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
    
    // Limites de la bola
    
    private Rectangle obtenerLimitesBola() {
        return new Rectangle (x, y, diametro, diametro);
    }
    
    // limites de paletas
    
    private Rectangle obtenerLimitesPaleta(Paleta paleta) {
        return new Rectangle(
                paleta.getX(),
                paleta.getY(),
                paleta.getAncho(),
                paleta.getAlto()
        );
        
    }
    
    // verificar si choco con una paleta
    
    private void verificarChoquePaletas() {
        
        Rectangle limitesBola = obtenerLimitesBola();
        
        Rectangle limitesIzquierda = obtenerLimitesPaleta(paletaIzquierda);
        Rectangle limitesDerecha = obtenerLimitesPaleta(paletaDerecha);
        
        if (limitesBola.intersects(limitesIzquierda) && velocidadX < 0) {
            
            if(tipo == TipoBola.FANTASMA && fantasmaDisponible) {
                fantasmaDisponible = false;
            } else {
                
                if (tipo == TipoBola.CONGELANTE) {
                    congelarPaleta(paletaDerecha);
                }
                
                velocidadX = -velocidadX;
                x = paletaIzquierda.getX() + paletaIzquierda.getAncho();
            }            
        }
        
        if (limitesBola.intersects(limitesDerecha) && velocidadX > 0) {
            
            if(tipo == TipoBola.FANTASMA && fantasmaDisponible) {
                fantasmaDisponible = false;
            } else {
                
                if (tipo == TipoBola.CONGELANTE) {
                    congelarPaleta(paletaIzquierda);
                }
                
                velocidadX = -velocidadX;
                x = paletaDerecha.getX() - diametro;
            }           
        }
    }
    
    // efecto de la bola congelante
    private void congelarPaleta(Paleta paleta) {
        
        paleta.reducirVelocidad();
        
        Thread efectoCongelante = new Thread(() -> {
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                paleta.reducirVelocidad();
            }
        });
        efectoCongelante.start();
    }
    
    // puntaje de las bolas
    private void aplicarPuntaje(
            Jugador jugadorQueAnota,
            Jugador jugadorQueFalla) {
        
        switch (tipo) {
            
            case NORMAL:
                jugadorQueAnota.sumarPuntos(1);
                break;
                
            case NEGATIVA:
                jugadorQueFalla.sumarPuntos(-2);
                break;
                
            case BONUS:
                jugadorQueAnota.sumarPuntos(2);
                break;
                
            case RAPIDA:
            case FANTASMA:
            case CONGELANTE:
                jugadorQueAnota.sumarPuntos(1);
                break;
        }
    }
    
    // salida por los laterales
    private void verificarSalidaLateral(int anchoPanel) {
        
        // sale por el lado izquierdo
        if (x + diametro < 0) {
            aplicarPuntaje(jugador2, jugador1);
            activa = false;
        }
        // sale por la derecha
        if (x > anchoPanel) {
            aplicarPuntaje (jugador1, jugador2);
            activa = false;
        }
    }
    

}
