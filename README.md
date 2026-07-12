PING PONG MULTIHILO EXTREME

Integrantes:
- Yariela Matarrita Arias
- Ana Lucía Vargas Rodríguez

Descripción:
Aplicación gráfica desarrollada en Java Swing que simula un juego
de Ping Pong para dos jugadores. El sistema utiliza múltiples bolas,
hilos independientes, sincronización y una colección concurrente.

Requisitos:
- Java JDK 21.
- Apache NetBeans.
- Biblioteca Absolute Layout.

Instrucciones para ejecutar:
1. Descomprimir el archivo ZIP.
2. Abrir Apache NetBeans.
3. Seleccionar File > Open Project.
4. Elegir la carpeta PingPongExtreme.
5. Esperar a que NetBeans cargue el proyecto.
6. Ejecutar Clean and Build.
7. Presionar Run Project.

Controles:
- Jugador izquierdo: W para subir y S para bajar.
- Jugador derecho: flecha arriba y flecha abajo.
- Iniciar: comienza la partida.
- Pausar/Reanudar: detiene o continúa la partida.
- Reiniciar: reinicia jugadores, rondas, tiempo y bolas.

Dificultades:
- Fácil.
- Normal.
- Difícil.
- Extremo.

Tipos de bolas:
- Blanca: suma 1 punto.
- Roja: resta 2 puntos.
- Azul: suma 2 puntos.
- Amarilla: se mueve más rápido.
- Morada: atraviesa una paleta una vez.
- Celeste: reduce temporalmente la velocidad de la paleta rival.

Notas:
- Cada bola funciona mediante un hilo independiente.
- Las bolas se almacenan en CopyOnWriteArrayList.
