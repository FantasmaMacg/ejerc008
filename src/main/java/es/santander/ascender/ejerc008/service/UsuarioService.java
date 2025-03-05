package es.santander.ascender.ejerc008.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.santander.ascender.ejerc008.model.Usuario;
import es.santander.ascender.ejerc008.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Create
    public Usuario createUsuario(Usuario usuario) {
        if (usuario.getPersona() != null && usuarioRepository.findByPersona(usuario.getPersona()).isPresent()) {
            throw new IllegalArgumentException("La persona ya está asociada a otro usuario");
        }
        return usuarioRepository.save(usuario);
    }

    // Read (all)
    @Transactional(readOnly = true)
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Read (by ID)
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Update
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setUsername(usuarioDetails.getUsername());
            usuario.setPassword(usuarioDetails.getPassword());
            usuario.setPersona(usuarioDetails.getPersona());
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Delete
    public boolean deleteUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}