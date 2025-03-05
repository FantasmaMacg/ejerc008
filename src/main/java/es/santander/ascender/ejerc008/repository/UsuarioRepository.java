package es.santander.ascender.ejerc008.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import es.santander.ascender.ejerc008.model.Usuario;
import es.santander.ascender.ejerc008.model.Persona;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByPersona(Persona persona);
}