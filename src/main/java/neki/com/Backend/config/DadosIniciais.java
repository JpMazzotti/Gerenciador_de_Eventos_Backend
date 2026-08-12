package neki.com.Backend.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import neki.com.Backend.model.Administrador;
import neki.com.Backend.model.Evento;
import neki.com.Backend.repository.AdministradorRepository;
import neki.com.Backend.repository.EventoRepository;

@Configuration
public class DadosIniciais {

    @Bean
    public CommandLineRunner seed(AdministradorRepository adminRepository,
            EventoRepository eventoRepository,
            BCryptPasswordEncoder encoder) {
        return args -> {
            if (adminRepository.findByEmail("admin@neki.com").isEmpty()) {
                Administrador admin = adminRepository.save(
                        new Administrador("Administrador Demo", "admin@neki.com", encoder.encode("1234567")));

                eventoRepository.save(new Evento(
                        "Hackathon Neki",
                        LocalDate.of(2026, 10, 15),
                        "São Paulo - SP",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMVDrH0ChcOfDu5HBgk5QP8Fn5csNGJ9mCr8HFHe2uzw&s=10",
                        admin));

                eventoRepository.save(new Evento(
                        "Tech Talk Inteligência Artificial",
                        LocalDate.of(2026, 9, 5),
                        "Remoto",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTh90k7m6fd64JfUorswNcP9dQL15xJkKiklWUbFaxLdsBDLcsHFbZH2AY&s=10",
                        admin));

                eventoRepository.save(new Evento(
                        "Workshop Spring Boot",
                        LocalDate.of(2026, 11, 20),
                        "Rio de Janeiro - RJ",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8IqIj12c4hWLYmBMusDZuPQBuGY6ZbhziLCKbMJI8ig&s=10",
                        admin));
            }
        };
    }
}
