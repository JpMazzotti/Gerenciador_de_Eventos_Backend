package neki.com.Backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais do administrador para autenticação")
public class LoginRequest {

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	@Schema(example = "admin@neki.com")
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	@Schema(example = "senha123")
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
