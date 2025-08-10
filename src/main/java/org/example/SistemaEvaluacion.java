package org.example;

import java.util.Scanner;

class SistemaEvaluacion {


    public static double calcularPromedio(double[] notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.length;
    }


    public static char obtenerLiteral(double promedio) {
        if (promedio >= 90) {
            return 'A';
        } else if (promedio >= 80) {
            return 'B';
        } else if (promedio >= 70) {
            return 'C';
        } else if (promedio >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }


    public static boolean estaAprobado(char literal) {
        return literal == 'A' || literal == 'B' || literal == 'C';
    }


    public static void mostrarResultado(String nombre, double[] notas) {
        double promedio = calcularPromedio(notas);
        char literal = obtenerLiteral(promedio);
        boolean aprobado = estaAprobado(literal);

        System.out.println("\nEstudiante: " + nombre);

        // Mostrar las notas
        System.out.print("Notas: [");
        for (int i = 0; i < notas.length; i++) {
            System.out.printf("%.1f", notas[i]);
            if (i < notas.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        System.out.printf("Promedio: %.2f\n", promedio);
        System.out.println("Literal: " + literal);
        System.out.println("Resultado: " + (aprobado ? "Aprobado ✅" : "Reprobado ❌"));
    }


    public static void evaluarVariosEstudiantes(String[] nombres, double[][] notasEstudiantes) {
        System.out.println("\n=== EVALUACIÓN DE MÚLTIPLES ESTUDIANTES ===");

        int aprobados = 0;
        double sumaPromedios = 0;

        for (int i = 0; i < nombres.length; i++) {
            mostrarResultado(nombres[i], notasEstudiantes[i]);

            double promedio = calcularPromedio(notasEstudiantes[i]);
            sumaPromedios += promedio;

            if (estaAprobado(obtenerLiteral(promedio))) {
                aprobados++;
            }

            System.out.println("-".repeat(40));
        }

        // Mostrar estadísticas generales
        System.out.println("\n=== ESTADÍSTICAS GENERALES ===");
        System.out.printf("Total de estudiantes: %d\n", nombres.length);
        System.out.printf("Estudiantes aprobados: %d\n", aprobados);
        System.out.printf("Estudiantes reprobados: %d\n", nombres.length - aprobados);
        System.out.printf("Promedio general del grupo: %.2f\n", sumaPromedios / nombres.length);
        System.out.printf("Porcentaje de aprobación: %.1f%%\n", (double) aprobados / nombres.length * 100);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE EVALUACIÓN ACADÉMICA ===");
        System.out.println("Universidad Mariano Gálvez de Guatemala");
        System.out.println("Sede Portales - Programación II");
        System.out.println();

        // Datos de ejemplo predefinidos
        String[] nombresEjemplo = {
                "Ana Castillo",
                "Carlos Rodríguez",
                "María González",
                "José Pérez"
        };

        double[][] notasEjemplo = {
                {95.5, 87.0, 90.0},      // Ana Castillo - Promedio: 90.83 (A)
                {75.0, 80.0, 78.5},      // Carlos Rodríguez - Promedio: 77.83 (C)
                {85.0, 92.0, 88.0},      // María González - Promedio: 88.33 (B)
                {55.0, 60.0, 58.5}       // José Pérez - Promedio: 57.83 (F)
        };

        // Mostrar ejemplo con datos predefinidos
        evaluarVariosEstudiantes(nombresEjemplo, notasEjemplo);

        System.out.println("\n" + "=".repeat(50));

        // Opción para que el usuario ingrese sus propios datos
        System.out.print("¿Desea evaluar otros estudiantes? (s/n): ");
        String respuesta = scanner.nextLine().toLowerCase();

        if (respuesta.equals("s") || respuesta.equals("si")) {
            System.out.print("¿Cuántos estudiantes desea evaluar? ");
            int numEstudiantes = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            String[] nombres = new String[numEstudiantes];
            double[][] notasEstudiantes = new double[numEstudiantes][];

            for (int i = 0; i < numEstudiantes; i++) {
                System.out.printf("\nEstudiante %d:\n", i + 1);
                System.out.print("Nombre: ");
                nombres[i] = scanner.nextLine();

                System.out.print("¿Cuántas notas tiene este estudiante? ");
                int cantidadNotas = scanner.nextInt();

                notasEstudiantes[i] = new double[cantidadNotas];

                for (int j = 0; j < cantidadNotas; j++) {
                    System.out.printf("Nota %d: ", j + 1);
                    notasEstudiantes[i][j] = scanner.nextDouble();
                }
                scanner.nextLine(); // Limpiar buffer
            }

            // Evaluar los estudiantes ingresados por el usuario
            evaluarVariosEstudiantes(nombres, notasEstudiantes);
        }

        System.out.println("\n¡Gracias por usar el Sistema de Evaluación Académica!");
        scanner.close();
    }
}