package ar.edu.utn.frc.bda.alquileres.domain;

public enum EstadoAlquiler {
    INICIADO(1),
    FINALIZADO(2);

    private final int valor;

    EstadoAlquiler(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

