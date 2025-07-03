package pe.edu.upeu.sysalmacen.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.edu.upeu.sysalmacen.mappers.RepuestosMapper;
import pe.edu.upeu.sysalmacen.mappers.SrSinstockMapper;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.model.SrSinstock;
import pe.edu.upeu.sysalmacen.service.IRepuestosService;
import pe.edu.upeu.sysalmacen.service.ISrSinstockService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/san_sr_sinstock")
@RequiredArgsConstructor
public class SrSinstockController {

    private final SrSinstockMapper SrSinstockMapper;
    private final ISrSinstockService service;

    @GetMapping
    public ResponseEntity<List<SrSinstock>> findAll() {
        List<SrSinstock> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SrSinstock> findById(@PathVariable("id") Long id) {
        SrSinstock obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SrSinstock dto) {
        SrSinstock obj = service.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdSinstock())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SrSinstock> update(@PathVariable("id") Long id, @RequestBody SrSinstock dto) {
        dto.setIdSinstock(id);
        SrSinstock obj = service.update(id, dto);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
