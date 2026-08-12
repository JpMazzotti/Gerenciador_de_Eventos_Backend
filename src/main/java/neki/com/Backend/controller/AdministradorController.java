package neki.com.Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import neki.com.Backend.dto.CadastroRequest;
import neki.com.Backend.dto.LoginRequest;
import neki.com.Backend.dto.LoginResponse;
import neki.com.Backend.service.AdministradorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Cadastro e login de administradores")
public class AdministradorController {

	private final AdministradorService service;

	public AdministradorController(AdministradorService service) {
		this.service = service;
	}

	@PostMapping("/cadastro")
	@Operation(summary = "Cadastra um novo administrador")
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastroRequest request) {
		service.cadastrar(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/login")
	@Operation(summary = "Autentica o administrador e retorna o token JWT")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		return ResponseEntity.ok(service.login(request));
	}
}

