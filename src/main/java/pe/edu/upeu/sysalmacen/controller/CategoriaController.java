package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.model.Categoria;
import pe.edu.upeu.sysalmacen.service.ICategoriaService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final ICategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
        List<Categoria> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable("id") Long id) {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Categoria dto) {
        Categoria obj = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdCategoria()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable("id") Long id, @RequestBody Categoria dto) {
        dto.setIdCategoria(id);
        Categoria obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
