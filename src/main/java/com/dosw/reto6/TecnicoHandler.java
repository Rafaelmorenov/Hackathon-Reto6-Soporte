package com.dosw.reto6;

public abstract class TecnicoHandler {
    protected TecnicoHandler siguiente;

    public void setSiguiente(TecnicoHandler siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejar(Ticket ticket);
}
