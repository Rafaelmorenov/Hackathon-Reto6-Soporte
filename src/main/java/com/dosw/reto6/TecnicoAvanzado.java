package com.dosw.reto6;

public class TecnicoAvanzado extends TecnicoHandler {
    @Override
    public void manejar(Ticket ticket) {
        if (ticket.getNivel().equals("avanzado") || ticket.getPrioridad() == 3) {
            ticket.resolver("Técnico Avanzado");
            System.out.println("✓ Ticket resuelto por Técnico Avanzado");
        } else if (siguiente != null) {
            siguiente.manejar(ticket);
        } else {
            ticket.marcarPendiente();
            System.out.println("✗ Ticket pendiente de escalamiento");
        }
    }
}
