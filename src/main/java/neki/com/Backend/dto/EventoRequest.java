package neki.com.Backend.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criar um novo evento")
public class EventoRequest {

	@NotBlank(message = "Nome do evento é obrigatório")
	@Schema(example = "Hackathon Neki")
	private String nome;

	@NotNull(message = "Data é obrigatória")
	@Schema(example = "2026-10-15", type = "string", format = "date")
	private LocalDate data;

	@NotBlank(message = "Localização é obrigatória")
	@Schema(example = "São Paulo - SP")
	private String localizacao;

	@Schema(example = "https://www.neki.com.br/imagem-evento.png")
	private String imagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
}

