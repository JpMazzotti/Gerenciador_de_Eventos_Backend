package neki.com.Backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import neki.com.Backend.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	List<Evento> findByAdministradorId(Long administradorId);

	Optional<Evento> findByIdAndAdministradorId(Long id, Long administradorId);
}
