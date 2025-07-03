package pe.edu.upeu.sysalmacen.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.mappers.HerramientasMapper;
import pe.edu.upeu.sysalmacen.mappers.RepuestosMapper;
import pe.edu.upeu.sysalmacen.model.Herramientas;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.service.IHerramientasService;
import pe.edu.upeu.sysalmacen.service.IRepuestosService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/herramientas")
public class HerramientasController {


    private final HerramientasMapper repuestosMapper;
    private final IHerramientasService service;

    @GetMapping
    public ResponseEntity<List<Herramientas>> findAll() {
        List<Herramientas> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Herramientas> findById(@PathVariable("id") Long id) {
        Herramientas obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Herramientas dto) {
        Herramientas obj = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdHerramientas())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Herramientas> update(@PathVariable("id") Long id, @RequestBody Herramientas dto) {
        dto.setIdHerramientas(id);
        Herramientas obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
