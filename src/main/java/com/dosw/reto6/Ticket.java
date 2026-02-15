package com.dosw.reto6;

public class Ticket {
    private final String nivel;
    private final int prioridad;
    private final String descripcion;
    private String estado = "Pendiente";
    private String tecnicoAsignado = "Ninguno";

    public Ticket(String nivel, int prioridad, String descripcion) {
        this.nivel = nivel.toLowerCase();
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    public String getNivel() { return nivel; }
    public int getPrioridad() { return prioridad; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
    public String getTecnicoAsignado() { return tecnicoAsignado; }

    public void resolver(String nombreTecnico) {
        this.estado = "Resuelto";
        this.tecnicoAsignado = nombreTecnico;
    }

    public void marcarPendiente() {
        this.estado = "Pendiente de escalamiento";
    }

    @Override
    public String toString() {
        return String.format("[%s] Nivel: %s, Prioridad: %d - %s (Asignado a: %s)",
                estado, nivel, prioridad, descripcion, tecnicoAsignado);
    }
}
