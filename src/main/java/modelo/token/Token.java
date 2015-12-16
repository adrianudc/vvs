package modelo.token;

/**
 *
 */
public class Token {

    /**
     *
     */
    private int contador = 0;

    /**
     *
     */
    private final String valor;

    /**
     *
     * @param valor del token a crear
     */
    public Token(final String valor) {
        this.valor = valor;
    }

    /**
     *
     * @return valor del contador
     */
    public int getContador() {
        return contador;
    }

    /**
     *
     * @param contador -
     */
    public void setContador(final int contador) {
        this.contador = contador;
    }

    /**
     *
     * @return valor del token
     */
    public String getValor() {
        return valor;
    }

}
