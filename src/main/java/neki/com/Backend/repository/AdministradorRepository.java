package neki.com.Backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import neki.com.Backend.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

	Optional<Administrador> findByEmail(String email);

	boolean existsByEmail(String email);
}