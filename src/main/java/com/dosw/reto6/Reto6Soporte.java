package com.dosw.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Reto6Soporte {
    private List<Ticket> tickets = new ArrayList<>();
    private TecnicoHandler cadena;

    public Reto6Soporte() {
        configurarCadena();
    }

    private void configurarCadena() {
        TecnicoHandler basico = new TecnicoBasico();
        TecnicoHandler intermedio = new TecnicoIntermedio();
        TecnicoHandler avanzado = new TecnicoAvanzado();

        basico.setSiguiente(intermedio);
        intermedio.setSiguiente(avanzado);

        this.cadena = basico;
    }

    public void ingresarTickets() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuántos tickets desea ingresar? ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\n--- Ticket #" + i + " ---");
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Nivel (basico/intermedio/avanzado): ");
            String nivel = scanner.nextLine();

            System.out.print("Prioridad (1=baja, 2=media, 3=alta): ");
            int prioridad = scanner.nextInt();
            scanner.nextLine();

            tickets.add(new Ticket(nivel, prioridad, descripcion));
        }
    }

    public void procesarTickets() {
        System.out.println("\n=== PROCESANDO TICKETS ===");
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println("\nTicket #" + (i + 1) + ": " + tickets.get(i).getDescripcion());
            cadena.manejar(tickets.get(i));
        }
    }

    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS ===");

        long resueltos = tickets.stream()
                .filter(t -> t.getEstado().equals("Resuelto"))
                .count();

        long pendientes = tickets.stream()
                .filter(t -> t.getEstado().contains("Pendiente"))
                .count();

        double promedioPrioridad = tickets.stream()
                .filter(t -> t.getEstado().equals("Resuelto"))
                .mapToInt(Ticket::getPrioridad)
                .average()
                .orElse(0.0);

        Map<String, Long> ticketsPorNivel = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getNivel, Collectors.counting()));

        Map<String, Long> ticketsPorTecnico = tickets.stream()
                .filter(t -> t.getEstado().equals("Resuelto"))
                .collect(Collectors.groupingBy(Ticket::getTecnicoAsignado, Collectors.counting()));

        System.out.println("Total de tickets: " + tickets.size());
        System.out.println("Tickets resueltos: " + resueltos);
        System.out.println("Tickets pendientes: " + pendientes);
        System.out.printf("Promedio de prioridad (resueltos): %.2f\n", promedioPrioridad);

        System.out.println("\nTickets por nivel:");
        ticketsPorNivel.forEach((nivel, count) -> 
            System.out.println("  " + nivel + ": " + count));

        System.out.println("\nTickets resueltos por técnico:");
        ticketsPorTecnico.forEach((tecnico, count) -> 
            System.out.println("  " + tecnico + ": " + count));
    }

    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DE TICKETS ===");
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println((i + 1) + ". " + tickets.get(i));
        }
    }

    public static void main(String[] args) {
        Reto6Soporte sistema = new Reto6Soporte();
        sistema.ingresarTickets();
        sistema.procesarTickets();
        sistema.mostrarResumen();
        sistema.mostrarEstadisticas();
    }
}
