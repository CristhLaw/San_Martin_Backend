package pe.edu.upeu.sysalmacen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.mappers.RepuestosMapper;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.service.IRepuestosService;
import pe.edu.upeu.sysalmacen.service.IRepuestosService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/repuesto")
public class RepuestosController {

    private final RepuestosMapper repuestosMapper;
    private final IRepuestosService service;

    @GetMapping
    public ResponseEntity<List<Repuestos>> findAll() {
        List<Repuestos> list = service.findAll();
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Repuestos> findById(@PathVariable("id") Long id) {
        Repuestos obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Repuestos dto) {
        Repuestos obj = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdRepuestos())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuestos> update(@PathVariable("id") Long id, @RequestBody Repuestos dto) {
        dto.setIdRepuestos(id);
        Repuestos obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
