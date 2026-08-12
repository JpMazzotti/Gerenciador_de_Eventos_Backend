package neki.com.Backend.dto;

import java.time.LocalDate;

import neki.com.Backend.model.Evento;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Evento retornado pela API")
public class EventoResponse {

	private Long id;

	private String nome;

	private LocalDate data;

	private String localizacao;

	private String imagem;

	public EventoResponse(Long id, String nome, LocalDate data, String localizacao, String imagem) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.localizacao = localizacao;
		this.imagem = imagem;
	}

	public static EventoResponse from(Evento evento) {
		return new EventoResponse(
				evento.getId(),
				evento.getNome(),
				evento.getData(),
				evento.getLocalizacao(),
				evento.getImagem());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
