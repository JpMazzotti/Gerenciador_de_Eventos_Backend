package neki.com.Backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import neki.com.Backend.dto.EventoAtualizadoRequest;
import neki.com.Backend.dto.EventoRequest;
import neki.com.Backend.dto.EventoResponse;
import neki.com.Backend.exception.CredenciaisInvalidasException;
import neki.com.Backend.exception.EventoNaoEncontradoException;
import neki.com.Backend.model.Administrador;
import neki.com.Backend.model.Evento;
import neki.com.Backend.repository.AdministradorRepository;
import neki.com.Backend.repository.EventoRepository;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;
	private final AdministradorRepository administradorRepository;

	public EventoService(EventoRepository eventoRepository, AdministradorRepository administradorRepository) {
		this.eventoRepository = eventoRepository;
		this.administradorRepository = administradorRepository;
	}

	@Transactional(readOnly = true)
	public List<EventoResponse> listar(Long adminId) {
		return eventoRepository.findByAdministradorId(adminId).stream()
				.map(EventoResponse::from)
				.toList();
	}

	@Transactional
	public EventoResponse criar(Long adminId, EventoRequest request) {
		Administrador administrador = administradorRepository.findById(adminId)
				.orElseThrow(() -> new CredenciaisInvalidasException("Administrador não encontrado."));

		Evento evento = new Evento(
				request.getNome(),
				request.getData(),
				request.getLocalizacao(),
				request.getImagem(),
				administrador);

		return EventoResponse.from(eventoRepository.save(evento));
	}

	@Transactional
	public EventoResponse atualizar(Long adminId, Long eventoId, EventoAtualizadoRequest request) {
		Evento evento = buscarEventoDoAdmin(adminId, eventoId);
		evento.setData(request.getData());
		evento.setLocalizacao(request.getLocalizacao());
		return EventoResponse.from(evento);
	}

	@Transactional
	public void excluir(Long adminId, Long eventoId) {
		Evento evento = buscarEventoDoAdmin(adminId, eventoId);
		eventoRepository.delete(evento);
	}

	private Evento buscarEventoDoAdmin(Long adminId, Long eventoId) {
		return eventoRepository.findByIdAndAdministradorId(eventoId, adminId)
				.orElseThrow(() -> new EventoNaoEncontradoException("Evento não encontrado para este administrador."));
	}
}
