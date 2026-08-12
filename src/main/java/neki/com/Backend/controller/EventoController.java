package neki.com.Backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neki.com.Backend.dto.EventoRequest;
import neki.com.Backend.dto.EventoResponse;
import neki.com.Backend.dto.EventoAtualizadoRequest;
import neki.com.Backend.service.EventoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "CRUD de eventos do administrador autenticado")
@SecurityRequirement(name = "bearerAuth")
public class EventoController {

	private final EventoService service;

	public EventoController(EventoService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Lista os eventos do administrador autenticado")
	public ResponseEntity<List<EventoResponse>> listar(@AuthenticationPrincipal Long adminId) {
		return ResponseEntity.ok(service.listar(adminId));
	}

	@PostMapping
	@Operation(summary = "Cria um novo evento associado ao administrador autenticado")
	public ResponseEntity<EventoResponse> criar(@AuthenticationPrincipal Long adminId,
			@Valid @RequestBody EventoRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(adminId, request));
	}

	@PutMapping("/{eventoId}")
	@Operation(summary = "Atualiza a data e a localização de um evento")
	public ResponseEntity<EventoResponse> atualizar(@AuthenticationPrincipal Long adminId,
			@PathVariable Long eventoId,
			@Valid @RequestBody EventoAtualizadoRequest request) {
		return ResponseEntity.ok(service.atualizar(adminId, eventoId, request));
	}

	@DeleteMapping("/{eventoId}")
	@Operation(summary = "Exclui um evento")
	public ResponseEntity<Void> excluir(@AuthenticationPrincipal Long adminId,
			@PathVariable Long eventoId) {
		service.excluir(adminId, eventoId);
		return ResponseEntity.noContent().build();
	}
}


