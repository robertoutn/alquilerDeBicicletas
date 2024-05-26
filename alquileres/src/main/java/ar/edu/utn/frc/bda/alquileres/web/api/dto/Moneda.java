package ar.edu.utn.frc.bda.alquileres.web.api.dto;

public enum Moneda {
    USD("USD"),
    ARS("ARS"),
    EUR("EUR"),
    CLP("CLP"),
    BRL("BRL"),
    COP("COP"),
    PEN("PEN"),
    GBP("GBP")
    ;

    private final String valor;



    Moneda(String valor) {
        this.valor = valor;
    }
    public String getValor() {
        return valor;
    }
}
