/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelBolas extends JPanel {
    
    private final GestorBolas gestorBolas;
    
    public PanelBolas(GestorBolas gestorBolas) {
        this.gestorBolas = gestorBolas;
        
        setOpaque(false);
        setFocusable(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Bola bola : gestorBolas.getBolas()) {
            
            g.setColor(obtenerColor(bola.getTipo()));
            
            g.fillOval(
                    bola.getX(),
                    bola.getY(),
                    bola.getDiametro(),
                    bola.getDiametro()
            );
        }
    }
    
    private Color obtenerColor(TipoBola tipo) {
        
        switch (tipo) {
            
            case NEGATIVA:
                return Color.RED;
                
            case BONUS:
                return Color.BLUE;
                
            case RAPIDA:
                return Color.YELLOW;
                
            case FANTASMA:
                return new Color(128, 0, 128);
            
            case CONGELANTE:
                return Color.CYAN;
                
            case NORMAL:
            default:
                return Color.WHITE;                
        }
    }
}
