package org.example.practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Tablero {
    private static final int DIMENSION = 30;
    private int[][] estadoActual = new int[DIMENSION][DIMENSION];
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];


    public void leerEstadoActual() {
        File file = new File("matriz");

        if (!file.exists()) {
            System.out.println("Archivo 'matriz' no encontrado. Generando uno aleatorio...");
            generarEstadoInicialAleatorio();
            return;
        }

        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < DIMENSION; i++) {
                String linea = scanner.nextLine();
                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual[i][j] = Character.getNumericValue(linea.charAt(j));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo 'matriz'.");
        }
    }

    public void generarEstadoInicialAleatorio() {
        try {
            FileWriter writer = new FileWriter("matriz");
            Random random = new Random();

            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    int valor = random.nextDouble() < 0.2 ? 1 : 0; // 20%
                    writer.write(valor + "");
                    estadoActual[i][j] = valor;
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Archivo 'matriz' generado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo 'matriz'.");
        }
    }


    public void generarEstadoActualPorMontecarlo() {
        Random random = new Random();
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                estadoActual[i][j] = random.nextDouble() < 0.5 ? 1 : 0;
            }
        }
    }

    public void transitarAlEstadoSiguiente() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                int vecinosVivos = contarVecinosVivos(i, j);

                if (estadoActual[i][j] == 1) {
                    estadoSiguiente[i][j] = (vecinosVivos == 2 || vecinosVivos == 3) ? 1 : 0;
                } else {
                    estadoSiguiente[i][j] = (vecinosVivos == 3) ? 1 : 0;
                }
            }
        }

        copiarEstado();
    }

    private int contarVecinosVivos(int fila, int columna) {
        int vivos = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;

                if ((i != 0 || j != 0) && nuevaFila >= 0 && nuevaFila < DIMENSION && nuevaColumna >= 0 && nuevaColumna < DIMENSION) {
                    vivos += estadoActual[nuevaFila][nuevaColumna];
                }
            }
        }

        return vivos;
    }

    private void copiarEstado() {
        for (int i = 0; i < DIMENSION; i++) {
            System.arraycopy(estadoSiguiente[i], 0, estadoActual[i], 0, DIMENSION);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                sb.append(estadoActual[i][j] == 1 ? "X" : " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
