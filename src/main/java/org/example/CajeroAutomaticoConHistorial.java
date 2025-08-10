package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Cajero{

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> historialTransacciones = new ArrayList<>();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void mostrarMenu() {
        System.out.println("\n=== CAJERO AUTOMÁTICO ===");
        System.out.println("Cooperativa Guadalupana");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Depositar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Ver historial de transacciones");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }


    public static void consultarSaldo(double saldo) {
        System.out.println("\n--- CONSULTA DE SALDO ---");
        System.out.printf("Su saldo actual es: Q%.2f\n", saldo);

        // Registrar en historial
        String transaccion = String.format("Consulta de saldo - %s",
                LocalDateTime.now().format(formatter));
        historialTransacciones.add(transaccion);

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }


    public static void depositarDinero(double[] saldo) {
        System.out.println("\n--- DEPÓSITO ---");
        System.out.print("Ingrese el monto a depositar: Q");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        if (monto > 0) {
            saldo[0] += monto;
            System.out.printf("Depósito exitoso. Ha depositado Q%.2f\n", monto);
            System.out.printf("Su nuevo saldo es: Q%.2f\n", saldo[0]);

            // Registrar en historial
            String transaccion = String.format("Depósito de Q%.2f - %s",
                    monto, LocalDateTime.now().format(formatter));
            historialTransacciones.add(transaccion);

        } else {
            System.out.println("Error: El monto debe ser mayor a cero.");
        }

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }


    public static void retirarDinero(double[] saldo) {
        System.out.println("\n--- RETIRO ---");
        System.out.printf("Saldo disponible: Q%.2f\n", saldo[0]);
        System.out.print("Ingrese el monto a retirar: Q");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        if (monto > 0) {
            if (monto <= saldo[0]) {
                saldo[0] -= monto;
                System.out.printf("Retiro exitoso. Ha retirado Q%.2f\n", monto);
                System.out.printf("Su nuevo saldo es: Q%.2f\n", saldo[0]);

                // Registrar en historial
                String transaccion = String.format("Retiro de Q%.2f - %s",
                        monto, LocalDateTime.now().format(formatter));
                historialTransacciones.add(transaccion);

            } else {
                System.out.printf("Error: Fondos insuficientes. Su saldo es Q%.2f\n", saldo[0]);

                // Registrar intento fallido en historial
                String transaccion = String.format("Intento de retiro fallido Q%.2f (fondos insuficientes) - %s",
                        monto, LocalDateTime.now().format(formatter));
                historialTransacciones.add(transaccion);
            }
        } else {
            System.out.println("Error: El monto debe ser mayor a cero.");
        }

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }

    public static void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE TRANSACCIONES ---");

        if (historialTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas en esta sesión.");
        } else {
            System.out.println("Transacciones realizadas:");
            System.out.println("-".repeat(50));

            for (int i = 0; i < historialTransacciones.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, historialTransacciones.get(i));
            }

            System.out.println("-".repeat(50));
            System.out.printf("Total de transacciones: %d\n", historialTransacciones.size());
        }

        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }


    public static void imprimirResumenSesion(double saldoInicial, double saldoFinal) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("RESUMEN DE LA SESIÓN");
        System.out.println("=".repeat(50));

        System.out.printf("Saldo inicial: Q%.2f\n", saldoInicial);
        System.out.printf("Saldo final: Q%.2f\n", saldoFinal);
        System.out.printf("Diferencia: Q%.2f\n", saldoFinal - saldoInicial);

        System.out.println("\nHistorial completo de transacciones:");
        System.out.println("-".repeat(50));

        if (historialTransacciones.isEmpty()) {
            System.out.println("No se realizaron transacciones.");
        } else {
            for (String transaccion : historialTransacciones) {
                System.out.println("• " + transaccion);
            }
        }

        System.out.println("-".repeat(50));
        System.out.println("Gracias por usar los servicios de Cooperativa Guadalupana");
    }


    public static void iniciarSesion() {
        // Saldo inicial del usuario
        double[] saldo = {1500.00}; // Usamos arreglo para simular referencia
        double saldoInicial = saldo[0]; // Guardar saldo inicial para el resumen
        int opcion = 0;

        System.out.println("Bienvenido al Cajero Automático");
        System.out.println("Cooperativa Guadalupana");
        System.out.printf("Su saldo inicial es: Q%.2f\n", saldo[0]);

        do {
            mostrarMenu();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        consultarSaldo(saldo[0]);
                        break;
                    case 2:
                        depositarDinero(saldo);
                        break;
                    case 3:
                        retirarDinero(saldo);
                        break;
                    case 4:
                        mostrarHistorial();
                        break;
                    case 5:
                        imprimirResumenSesion(saldoInicial, saldo[0]);
                        System.out.println("\n¡Que tenga un buen día!");
                        break;
                    default:
                        System.out.println("\nOpción no válida. Intente nuevamente.");
                        System.out.println("Presione Enter para continuar...");
                        scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("\nError: Ingrese un número válido.");
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine(); // Limpiar buffer adicional
                opcion = 0; // Reiniciar opción para continuar el bucle
            }

        } while (opcion != 5);
    }

    public static void main(String[] args) {
        iniciarSesion();
        scanner.close();
    }
}