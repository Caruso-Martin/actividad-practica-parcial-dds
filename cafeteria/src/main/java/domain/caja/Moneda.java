package domain.caja;

public enum Moneda {
    PESO,
    DOLAR,
    EURO;

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
}
