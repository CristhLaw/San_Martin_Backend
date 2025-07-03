package pe.edu.upeu.sysalmacen.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.mappers.SolicitudRepuestoMapper;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;
import pe.edu.upeu.sysalmacen.service.ISolicitudRepuestoService;
import pe.edu.upeu.sysalmacen.service.impl.SolicitudRepuestoServiceImp;

import java.util.List;

@RestController
@RequestMapping("/solicitudrepo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite llamadas desde Angular u otros dominios
public class SolicitudRepuestoController {

    private final ISolicitudRepuestoService solicitudService;
    private final SolicitudRepuestoMapper solicitudMapper;
    private final SolicitudRepuestoServiceImp solicitudRepuestoServiceImp;

    // 🔹 Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<SolicitudRepuestoDTO>> findAll() {
        List<SolicitudRepuestoDTO> list = solicitudMapper.toDTOs(solicitudService.findAll());
        return ResponseEntity.ok(list);
    }

    // 🔹 Obtener una solicitud por su ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudRepuestoDTO> findById(@PathVariable Long id) {
        SolicitudRepuesto obj = solicitudService.findById(id);
        return ResponseEntity.ok(solicitudMapper.toDTO(obj));
    }

    // 🔹 Registrar una nueva solicitud con repuestos y herramientas
    @PostMapping
    public ResponseEntity<SolicitudRepuestoDTO> save(@Valid @RequestBody SolicitudRepuestoDTO.SolicitudRepuestoCADto dto) {
        SolicitudRepuestoDTO obj = solicitudService.saveD(dto);
        return ResponseEntity.ok(obj); // Retorna la solicitud registrada con sus detalles
    }

    // 🔹 Actualizar solicitud existente
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudRepuestoDTO> update(@Valid @RequestBody SolicitudRepuestoDTO.SolicitudRepuestoCADto dto,
                                                       @PathVariable Long id) {
        SolicitudRepuestoDTO obj = solicitudService.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }

    // 🔹 Eliminar una solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitudService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Listar solicitudes paginadasss
    @GetMapping("/pageable")
    public ResponseEntity<Page<SolicitudRepuestoDTO>> listPage(Pageable pageable) {
        Page<SolicitudRepuestoDTO> page = solicitudService.listaPage(pageable)
                .map(solicitudMapper::toDTO);
        return ResponseEntity.ok(page);
    }

    // Aprobar solicitud
    @PutMapping("/aprobar/{id}")
    public ResponseEntity<Void> aprobarSolicitud(@PathVariable Long id) {
        solicitudRepuestoServiceImp.aprobar(id);
        return ResponseEntity.ok().build();
    }

    // Rechazar solicitud
    @PutMapping("/rechazar/{id}")
    public ResponseEntity<Void> rechazarSolicitud(@PathVariable Long id) {
        solicitudRepuestoServiceImp.rechazar(id);
        return ResponseEntity.ok().build();
    }
}