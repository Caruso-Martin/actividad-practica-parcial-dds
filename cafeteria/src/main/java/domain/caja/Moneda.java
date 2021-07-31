package domain.caja;

public enum Moneda {
    PESO("Peso Argentino"),
    DOLAR("Dolar Estadounidense"),
    EURO("Euro");

    private String valor;

    Moneda(String valor) {
        this.valor = valor;
    }

    public static Moneda fromInteger(int idMoneda) {
        switch(idMoneda) {
            case 0:
                return PESO;
            case 1:
                return DOLAR;
            case 2:
                return EURO;
        }
        return null;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return this.getValor();
    }
}
