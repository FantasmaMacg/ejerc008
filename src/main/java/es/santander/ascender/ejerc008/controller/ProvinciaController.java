package es.santander.ascender.ejerc008.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santander.ascender.ejerc008.model.Provincia;
import es.santander.ascender.ejerc008.service.ProvinciaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;

    // Create
    @PostMapping
    public ResponseEntity<Provincia> createProvincia(@Valid @RequestBody Provincia provincia) {
        Provincia createdProvincia = provinciaService.createProvincia(provincia);
        return new ResponseEntity<>(createdProvincia, HttpStatus.CREATED);
    }

    // Read (all)
    @GetMapping
    public ResponseEntity<List<Provincia>> getAllProvincias() {
        List<Provincia> provincias = provinciaService.getAllProvincias();
        return new ResponseEntity<>(provincias, HttpStatus.OK);
    }

    // Read (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Provincia> getProvinciaById(@PathVariable Long id) {
        Optional<Provincia> provincia = provinciaService.getProvinciaById(id);
        return provincia.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Provincia> updateProvincia(@PathVariable Long id, @Valid @RequestBody Provincia provinciaDetails) {
        Provincia updatedProvincia = provinciaService.updateProvincia(id, provinciaDetails);
        return updatedProvincia != null ? new ResponseEntity<>(updatedProvincia, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}