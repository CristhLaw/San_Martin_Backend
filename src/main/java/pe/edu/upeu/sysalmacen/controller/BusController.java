package pe.edu.upeu.sysalmacen.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.mapstruct.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysalmacen.dtos.BusDTO;
import pe.edu.upeu.sysalmacen.exception.CustomResponse;
import pe.edu.upeu.sysalmacen.mappers.BusMapper;
import pe.edu.upeu.sysalmacen.model.Bus;
import pe.edu.upeu.sysalmacen.service.IBusService;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/buses")
public class BusController {

    private final IBusService busService;
    private final BusMapper BusMapper;


    @GetMapping
    public ResponseEntity<List<BusDTO>> findAll() {
        List<BusDTO> list = BusMapper.toDTOs(busService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> findById(@PathVariable("id") Long id) {
        Bus obj = busService.findById(id);
        return ResponseEntity.ok(BusMapper.toDTO(obj));
    }
    /*
     * Otra forma de llamar return ResponseEntity.created(location).build();
     * */
    @PostMapping
    public ResponseEntity<CustomResponse> save(@Valid @RequestBody BusDTO dto) {
        Bus obj = busService.save(BusMapper.toEntity(dto));
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMarca()).toUri();
        return ResponseEntity.ok(new CustomResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdbus())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusDTO> update(@Valid @PathVariable("id") Long id, @RequestBody BusDTO dto) {
        dto.setIdbus(id);
        Bus obj = busService.update(id, BusMapper.toEntity(dto));
        return ResponseEntity.ok(BusMapper.toDTO(obj));
    }
    /*
     * Otra forma de retornar - return ResponseEntity.noContent().build();
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable("id") Long id) {
        CustomResponse operacion= busService.delete(id);
        return ResponseEntity.ok(operacion);
    }

    @GetMapping("/buscarmaxid")
    public ResponseEntity<Long> getMarcaMaxId() {
        Long idMax = busService.maxID();
        return ResponseEntity.ok(idMax);
    }

    /*@GetMapping("/hateoas/{id}")
    public EntityModel<MarcaDTO> findByIdHateoas(@PathVariable("id") Long id) {
        EntityModel<MarcaDTO> resource = EntityModel.of(mapperUtil.map(service.findById(id), PatientDTO.class));

        //generar link informativo
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(MedicController.class).findAll());

        resource.add(link1.withRel("patient-self-info"));
        resource.add(link2.withRel("all-medic-info"));

        return resource;
    }*/


}
