package neki.com.Backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neki.com.Backend.dto.CadastroRequest;
import neki.com.Backend.dto.LoginRequest;
import neki.com.Backend.dto.LoginResponse;
import neki.com.Backend.exception.CredenciaisInvalidasException;
import neki.com.Backend.exception.EmailJaCadastradoException;
import neki.com.Backend.model.Administrador;
import neki.com.Backend.repository.AdministradorRepository;

@Service
public class AdministradorService {

	private final AdministradorRepository repository;
	private final BCryptPasswordEncoder encoder;
	private final TokenService tokenService;

	public AdministradorService(AdministradorRepository repository,
			BCryptPasswordEncoder encoder,
			TokenService tokenService) {
		this.repository = repository;
		this.encoder = encoder;
		this.tokenService = tokenService;
	}

	@Transactional
	public Administrador cadastrar(CadastroRequest request) {
		if (repository.existsByEmail(request.getEmail())) {
			throw new EmailJaCadastradoException("Já existe um administrador com o email informado.");
		}
		String senhaCriptografada = encoder.encode(request.getSenha());
		Administrador administrador = new Administrador(request.getNome(), request.getEmail(), senhaCriptografada);
		return repository.save(administrador);
	}

	public LoginResponse login(LoginRequest request) {
		Administrador administrador = repository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CredenciaisInvalidasException("Email ou senha inválidos."));

		if (!encoder.matches(request.getSenha(), administrador.getSenha())) {
			throw new CredenciaisInvalidasException("Email ou senha inválidos.");
		}

		String token = tokenService.gerarToken(administrador.getId());
		return new LoginResponse(token, administrador.getEmail(), administrador.getNome());
	}
}