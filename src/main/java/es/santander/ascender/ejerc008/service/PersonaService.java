package es.santander.ascender.ejerc008.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Provincia;
import es.santander.ascender.ejerc008.repository.PersonaRepository;
import es.santander.ascender.ejerc008.repository.ProvinciaRepository;

@Service
@Transactional
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    // Create
    public Persona createPersona(Persona persona) {
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
    public Persona updatePersona(Long id, Persona personaDetails) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setEmail(personaDetails.getEmail());
            persona.setProvincia(personaDetails.getProvincia());
            return personaRepository.save(persona);
        }
        return null;
    }

    // Delete
    public boolean deletePersona(Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}