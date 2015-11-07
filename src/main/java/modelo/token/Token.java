package modelo.token;

public class Token {

    private int contador = 0;

    private final String valor;

    public Token(String valor) {
        this.valor = valor;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public String getValor() {
        return valor;
    }

}
