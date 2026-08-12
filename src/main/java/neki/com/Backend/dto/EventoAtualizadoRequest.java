package neki.com.Backend.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para atualizar a data e/ou a localização de um evento")
public class EventoAtualizadoRequest {

	@NotNull(message = "Data é obrigatória")
	@Schema(example = "2026-11-20", type = "string", format = "date")
	private LocalDate data;

	@NotBlank(message = "Localização é obrigatória")
	@Schema(example = "Rio de Janeiro - RJ")
	private String localizacao;

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
}