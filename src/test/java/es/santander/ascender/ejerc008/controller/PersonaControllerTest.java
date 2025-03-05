package es.santander.ascender.ejerc008.controller;

import es.santander.ascender.ejerc008.model.Persona;
import es.santander.ascender.ejerc008.model.Usuario;
import es.santander.ascender.ejerc008.repository.PersonaRepository;
import es.santander.ascender.ejerc008.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        personaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    public void testCreatePersona() throws Exception {
        String personaJson = "{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\"}";

        mockMvc.perform(post("/api/personas?username=juan&password=password123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"))
                .andExpect(jsonPath("$.usuario.username").value("juan"));
    }

    @Test
    public void testCreatePersonaWithInvalidData() throws Exception {
        String personaJson = "{\"nombre\":\"\",\"apellido\":\"Perez\",\"email\":\"juan.perez@example.com\"}";

        mockMvc.perform(post("/api/personas?username=juan&password=password123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personaJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllPersonas() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juan.perez@example.com");
        persona.setUsuario(usuario);
        personaRepository.save(persona);

        mockMvc.perform(get("/api/personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].email").value("juan.perez@example.com"))
                .andExpect(jsonPath("$[0].usuario.username").value("juan"));
    }

    @Test
    public void testGetPersonaById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juan.perez@example.com");
        persona.setUsuario(usuario);
        persona = personaRepository.save(persona);

        mockMvc.perform(get("/api/personas/" + persona.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@example.com"))
                .andExpect(jsonPath("$.usuario.username").value("juan"));
    }

    @Test
    public void testUpdatePersona() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juan.perez@example.com");
        persona.setUsuario(usuario);
        persona = personaRepository.save(persona);

        String updatedPersonaJson = "{\"nombre\":\"Carlos\",\"apellido\":\"Gomez\",\"email\":\"carlos.gomez@example.com\"}";

        mockMvc.perform(put("/api/personas/" + persona.getId() + "?username=carlos&password=newpassword123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPersonaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.apellido").value("Gomez"))
                .andExpect(jsonPath("$.email").value("carlos.gomez@example.com"))
                .andExpect(jsonPath("$.usuario.username").value("carlos"));
    }

    @Test
    public void testUpdatePersonaWithoutChangingUser() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juan.perez@example.com");
        persona.setUsuario(usuario);
        persona = personaRepository.save(persona);

        String updatedPersonaJson = "{\"nombre\":\"Carlos\",\"apellido\":\"Gomez\",\"email\":\"carlos.gomez@example.com\"}";

        mockMvc.perform(put("/api/personas/" + persona.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPersonaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.apellido").value("Gomez"))
                .andExpect(jsonPath("$.email").value("carlos.gomez@example.com"))
                .andExpect(jsonPath("$.usuario.username").value("juan"));
    }

    @Test
    public void testDeletePersona() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("juan");
        usuario.setPassword("password123");
        usuario = usuarioRepository.save(usuario);

        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juan.perez@example.com");
        persona.setUsuario(usuario);
        persona = personaRepository.save(persona);

        mockMvc.perform(delete("/api/personas/" + persona.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/personas/" + persona.getId()))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/usuarios/" + usuario.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePersonaThatDoesNotExist() throws Exception {
        mockMvc.perform(delete("/api/personas/999"))
                .andExpect(status().isNotFound());
    }

   
}