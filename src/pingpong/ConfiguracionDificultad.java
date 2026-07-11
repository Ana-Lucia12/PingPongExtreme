/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pingpong;

/**
 *
 * @author Usuario
 */
public enum ConfiguracionDificultad {
    FACIL (3, 3000, 2, 20),
    NORMAL(5, 2200, 4, 35),
    DIFICIL(7, 1500, 6, 50),
    EXTREMO(9, 800, 8, 75);
    
    private final int velocidadBola;
    private final int frecuenciaAparicion;
    private final int maximoBolas;
    private final int probabilidadEspecial;
    
    ConfiguracionDificultad(
            int velocidadBola,
            int frecuenciaAparicion,
            int maximoBolas,
            int probabilidadEspecial) {
    
        this.velocidadBola = velocidadBola;
        this.frecuenciaAparicion = frecuenciaAparicion;
        this.maximoBolas = maximoBolas;
        this.probabilidadEspecial = probabilidadEspecial;
    }        
         
            
    public int getVelocidadBola() {
        return velocidadBola;
    }
    
    public int getFrecuenciaAparicion() {
        return frecuenciaAparicion;
    }
    
    public int getMaximoBolas() {
        return maximoBolas;
    }
    
    public int getProbabilidadEspecial() {
        return probabilidadEspecial;
    }
}
