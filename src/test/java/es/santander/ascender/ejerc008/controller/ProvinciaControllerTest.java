package es.santander.ascender.ejerc008.controller;

import es.santander.ascender.ejerc008.model.Provincia;
import es.santander.ascender.ejerc008.repository.ProvinciaRepository;
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
public class ProvinciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        provinciaRepository.deleteAll();
    }

    @Test
    public void testCreateProvincia() throws Exception {
        String provinciaJson = "{\"nombre\":\"Madrid\",\"codigo\":\"MAD\",\"descripcion\":\"Capital de España\"}";

        mockMvc.perform(post("/api/provincias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(provinciaJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Madrid"))
                .andExpect(jsonPath("$.codigo").value("MAD"))
                .andExpect(jsonPath("$.descripcion").value("Capital de España"));
    }

    @Test
    public void testGetAllProvincias() throws Exception {
        Provincia provincia = new Provincia();
        provincia.setNombre("Madrid");
        provincia.setCodigo("MAD");
        provincia.setDescripcion("Capital de España");
        provinciaRepository.save(provincia);

        mockMvc.perform(get("/api/provincias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Madrid"))
                .andExpect(jsonPath("$[0].codigo").value("MAD"))
                .andExpect(jsonPath("$[0].descripcion").value("Capital de España"));
    }

    @Test
    public void testGetProvinciaById() throws Exception {
        Provincia provincia = new Provincia();
        provincia.setNombre("Madrid");
        provincia.setCodigo("MAD");
        provincia.setDescripcion("Capital de España");
        provincia = provinciaRepository.save(provincia);

        mockMvc.perform(get("/api/provincias/" + provincia.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Madrid"))
                .andExpect(jsonPath("$.codigo").value("MAD"))
                .andExpect(jsonPath("$.descripcion").value("Capital de España"));
    }

    @Test
    public void testUpdateProvincia() throws Exception {
        Provincia provincia = new Provincia();
        provincia.setNombre("Madrid");
        provincia.setCodigo("MAD");
        provincia.setDescripcion("Capital de España");
        provincia = provinciaRepository.save(provincia);

        String updatedProvinciaJson = "{\"nombre\":\"Barcelona\",\"codigo\":\"BCN\",\"descripcion\":\"Ciudad en España\"}";

        mockMvc.perform(put("/api/provincias/" + provincia.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProvinciaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Barcelona"))
                .andExpect(jsonPath("$.codigo").value("BCN"))
                .andExpect(jsonPath("$.descripcion").value("Ciudad en España"));
    }
}