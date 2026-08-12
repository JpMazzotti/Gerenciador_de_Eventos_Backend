package neki.com.Backend.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import neki.com.Backend.exception.CredenciaisInvalidasException;
import neki.com.Backend.exception.EmailJaCadastradoException;
import neki.com.Backend.exception.EventoNaoEncontradoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmailJaCadastradoException.class)
	public ResponseEntity<Map<String, Object>> emailJaCadastrado(EmailJaCadastradoException ex) {
		return montarErro(HttpStatus.CONFLICT, ex.getMessage());
	}

	@ExceptionHandler(CredenciaisInvalidasException.class)
	public ResponseEntity<Map<String, Object>> credenciaisInvalidas(CredenciaisInvalidasException ex) {
		return montarErro(HttpStatus.UNAUTHORIZED, ex.getMessage());
	}

	@ExceptionHandler(EventoNaoEncontradoException.class)
	public ResponseEntity<Map<String, Object>> eventoNaoEncontrado(EventoNaoEncontradoException ex) {
		return montarErro(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> validacao(MethodArgumentNotValidException ex) {
		String mensagem = ex.getBindingResult().getFieldErrors().stream()
				.map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
				.findFirst()
				.orElse("Requisição inválida.");
		return montarErro(HttpStatus.BAD_REQUEST, mensagem);
	}

	private ResponseEntity<Map<String, Object>> montarErro(HttpStatus status, String mensagem) {
		Map<String, Object> corpo = new LinkedHashMap<>();
		corpo.put("status", status.value());
		corpo.put("erro", status.getReasonPhrase());
		corpo.put("mensagem", mensagem);
		return ResponseEntity.status(status).body(corpo);
	}
}