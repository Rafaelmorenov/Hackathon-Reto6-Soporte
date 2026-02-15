package com.dosw.reto6;

public class TecnicoBasico extends TecnicoHandler {
    @Override
    public void manejar(Ticket ticket) {
        if (ticket.getNivel().equals("basico") && ticket.getPrioridad() == 1) {
            ticket.resolver("Técnico Básico");
            System.out.println("Ticket resuelto por Técnico Básico");
        } else if (siguiente != null) {
            siguiente.manejar(ticket);
        } else {
            ticket.marcarPendiente();
            System.out.println("Ticket pendiente de escalamiento");
        }
    }
}
