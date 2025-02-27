package org.example;

import org.example.practica1.Matematicas;
import org.example.practica2.Tablero;
import org.example.practica3.Graph;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        long pasos = Long.parseLong(args[0]);

        double piAproximado = Matematicas.generarNumeroPi(pasos);

        System.out.println("\nPRACTICA 1:\n");
        System.out.println("Aproximación de PI con " + pasos + " puntos: " + piAproximado);
        System.out.println("\n-------------------------------------------------");
        System.out.println("\nPRACTICA 2:\n");

        try {
            Tablero tablero = new Tablero();

            System.out.println("SIMULACIÓN CON TABLERO LEÍDO\n");
            tablero.leerEstadoActual();
            System.out.println(tablero);
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }

            System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO\n");
            tablero.generarEstadoActualPorMontecarlo();
            System.out.println(tablero);
            for (int i = 0; i < 15; i++) {
                TimeUnit.SECONDS.sleep(1);
                tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\n-------------------------------------------------");
        System.out.println("\nPRACTICA 3:\n");

        Graph<Integer> grafo = new Graph<>();

        grafo.addEdge(1, 2);
        grafo.addEdge(1, 5);
        grafo.addEdge(5, 6);
        grafo.addEdge(6, 4);
        grafo.addEdge(3, 4);

        System.out.println("Lista de adyacencia del grafo:");
        System.out.println(grafo);

        int inicio = 1, destino = 4;
        List<Integer> camino = grafo.onePath(inicio, destino);


        if (camino != null) {
            System.out.println("Camino encontrado entre " + inicio + " y " + destino + ": " + camino);
        } else {
            System.out.println("No hay camino entre " + inicio + " y " + destino);
        }
    }
}





