package es.santander.ascender.ejerc008.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Usuario;
import es.santander.ascender.ejerc008.repository.PersonaRepository;
import es.santander.ascender.ejerc008.repository.UsuarioRepository;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Create
    public Persona createPersona(Persona persona) {
        if (persona.getUsuario() != null && personaRepository.findByUsuario(persona.getUsuario()).isPresent()) {
            throw new IllegalArgumentException("El usuario ya está asociado a otra persona");
        }
        return personaRepository.save(persona);
    }

    // Read (all)
    @Transactional(readOnly = true)
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Read (by ID)
    @Transactional(readOnly = true)
    public Optional<Persona> getPersonaById(Long id) {
        return personaRepository.findById(id);
    }

    // Update
    public Persona updatePersona(Long id, Persona personaDetails, String username, String password) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEmail(personaDetails.getEmail());
            persona.setProvincia(personaDetails.getProvincia());

            Usuario usuario = persona.getUsuario();
            if (usuario != null) {
                if (username != null && !username.isEmpty()) {
                    usuario.setUsername(username);
                }
                if (password != null && !password.isEmpty()) {
                    usuario.setPassword(password);
                }
                usuarioRepository.save(usuario);
            }

            return personaRepository.save(persona);
        }
        return null;
    }

    // Delete
    public boolean deletePersona(Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            Usuario usuario = persona.getUsuario();
            personaRepository.deleteById(id);
            if (usuario != null) {
                usuarioRepository.deleteById(usuario.getId());
            }
            return true;
        }
        return false;
    }
}