package neki.com.Backend.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta do login com o token JWT")
public class LoginResponse {

	@Schema(description = "Token JWT que deve ser enviado no header Authorization das demais chamadas")
	private String token;

	@Schema(example = "admin@neki.com")
	private String email;

	@Schema(example = "Administrador Demo")
	private String nome;

	public LoginResponse(String token, String email, String nome) {
		this.token = token;
		this.email = email;
		this.nome = nome;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}

