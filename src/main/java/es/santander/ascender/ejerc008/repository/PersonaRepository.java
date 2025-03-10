package es.santander.ascender.ejerc008.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Usuario;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByUsuario(Usuario usuario);
}