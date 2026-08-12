package neki.com.Backend.exception;


public class EventoNaoEncontradoException extends RuntimeException {

	public EventoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
