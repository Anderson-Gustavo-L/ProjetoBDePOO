package byteassistencia.exception;

public class PagamentoInsuficienteException extends RuntimeException {
    public PagamentoInsuficienteException(String message) {
        super(message);
    }
}