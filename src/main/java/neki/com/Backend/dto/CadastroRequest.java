package neki.com.Backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastrar um novo administrador")
public class CadastroRequest {

	@NotBlank(message = "Nome é obrigatório")
	@Schema(example = "João Silva")
	private String nome;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	@Schema(example = "joao@neki.com")
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	@Schema(example = "senha123")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
