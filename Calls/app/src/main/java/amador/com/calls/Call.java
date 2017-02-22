package amador.com.calls;

/**
 * Created by usuario on 10/02/17.
 */

public class Call {

    private String numero;
    private String tipo;
    private String duracion;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return numero + ", "+tipo;
    }
}
