package com.dosw.reto6;

public class TecnicoIntermedio extends TecnicoHandler {
    @Override
    public void manejar(Ticket ticket) {
        if (ticket.getNivel().equals("intermedio") && ticket.getPrioridad() <= 2) {
            ticket.resolver("Técnico Intermedio");
            System.out.println("Ticket resuelto por Técnico Intermedio");
        } else if (siguiente != null) {
            siguiente.manejar(ticket);
        } else {
            ticket.marcarPendiente();
            System.out.println("Ticket pendiente de escalamiento");
        }
    }
}
