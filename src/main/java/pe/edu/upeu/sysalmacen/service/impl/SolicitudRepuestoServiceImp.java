package pe.edu.upeu.sysalmacen.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.sysalmacen.dtos.*;
import pe.edu.upeu.sysalmacen.mappers.SolicitudRepuestoMapper;
import pe.edu.upeu.sysalmacen.model.DetalleHerramienta;
import pe.edu.upeu.sysalmacen.model.DetalleRepuesto;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;
import pe.edu.upeu.sysalmacen.repository.*;
import pe.edu.upeu.sysalmacen.service.ISolicitudRepuestoService;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SolicitudRepuestoServiceImp extends CrudGenericoServiceImp<SolicitudRepuesto, Long>
        implements ISolicitudRepuestoService {

    @Autowired
    private DataSource dataSource;

    private final ISolicitudRepuestoRepository repo;
    private final SolicitudRepuestoMapper solicitudMapper;
    private final IBusRepository busRepository;
    private final IRepuestosRepository repuestosRepository;
    private final IHerramientasRepository herramientasRepository;
    private final IUsuarioRepository usuarioRepository;
    @Autowired
    private ISolicitudRepuestoRepository iSolicitudRepuestoRepository;

    @Override
    protected ICrudGenericoRepository<SolicitudRepuesto, Long> getRepo() {
        return repo;
    }

    // 🔹 Guardar solicitud
    @Override
    public SolicitudRepuestoDTO saveD(SolicitudRepuestoDTO.SolicitudRepuestoCADto dto) {
        SolicitudRepuesto solicitud = new SolicitudRepuesto();
        solicitud.setDescripcionDeFalla(dto.descripcionDeFalla());
        solicitud.setEstado(dto.estado());
        solicitud.setUsuario(usuarioRepository.findById(dto.usuario()).orElseThrow(() ->
                new EntityNotFoundException("Usuario no encontrado")));
        solicitud.setBus(busRepository.findById(dto.bus()).orElseThrow(() ->
                new EntityNotFoundException("Bus no encontrado")));

        SolicitudRepuesto solicitudGuardada = repo.save(solicitud);

        // ✅ Listas MUTABLES
        List<DetalleRepuesto> detalleRepuestos = (dto.repuestos() != null)
                ? new ArrayList<>(dto.repuestos().stream().map(r -> DetalleRepuesto.builder()
                .solicitud(solicitudGuardada)
                .repuesto(repuestosRepository.findById(r.idRepuesto()).orElseThrow(() ->
                        new EntityNotFoundException("Repuesto con ID " + r.idRepuesto() + " no encontrado")))
                .cantidad(r.cantidad())
                .build()).toList())
                : new ArrayList<>();

        List<DetalleHerramienta> detalleHerramientas = (dto.herramientas() != null)
                ? new ArrayList<>(dto.herramientas().stream().map(h -> DetalleHerramienta.builder()
                .solicitud(solicitudGuardada)
                .herramienta(herramientasRepository.findById(h.idHerramienta()).orElseThrow(() ->
                        new EntityNotFoundException("Herramienta con ID " + h.idHerramienta() + " no encontrada")))
                .cantidad(h.cantidad())
                .build()).toList())
                : new ArrayList<>();

        solicitudGuardada.setDetalleRepuestos(detalleRepuestos);
        solicitudGuardada.setDetalleHerramientas(detalleHerramientas);

        SolicitudRepuesto finalGuardado = repo.save(solicitudGuardada);

        return solicitudMapper.toDTO(finalGuardado);
    }



    // 🔹 Actualizar solicitud
    @Override
    public SolicitudRepuestoDTO updateD(SolicitudRepuestoDTO.SolicitudRepuestoCADto dto, Long id) {
        SolicitudRepuesto existente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));

        existente.setDescripcionDeFalla(dto.descripcionDeFalla());
        existente.setEstado(dto.estado());
        existente.setBus(busRepository.findById(dto.bus())
                .orElseThrow(() -> new EntityNotFoundException("Bus no encontrado")));
        existente.setUsuario(usuarioRepository.findById(dto.usuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));

        existente.getDetalleRepuestos().clear();
        existente.getDetalleHerramientas().clear();

        List<DetalleRepuesto> nuevosRepuestos = (dto.repuestos() != null)
                ? new java.util.ArrayList<>(dto.repuestos().stream().map(r ->
                DetalleRepuesto.builder()
                        .solicitud(existente)
                        .repuesto(repuestosRepository.findById(r.idRepuesto())
                                .orElseThrow(() -> new EntityNotFoundException("Repuesto no encontrado")))
                        .cantidad(r.cantidad())
                        .build()
        ).toList())
                : new java.util.ArrayList<>();

        List<DetalleHerramienta> nuevasHerramientas = (dto.herramientas() != null)
                ? new java.util.ArrayList<>(dto.herramientas().stream().map(h ->
                DetalleHerramienta.builder()
                        .solicitud(existente)
                        .herramienta(herramientasRepository.findById(h.idHerramienta())
                                .orElseThrow(() -> new EntityNotFoundException("Herramienta no encontrada")))
                        .cantidad(h.cantidad())
                        .build()
        ).toList())
                : new java.util.ArrayList<>();

        existente.setDetalleRepuestos(nuevosRepuestos);
        existente.setDetalleHerramientas(nuevasHerramientas);

        SolicitudRepuesto actualizado = repo.save(existente);
        return solicitudMapper.toDTO(actualizado);
    }



    // 🔹 Listar todo
    @Override
    public List<SolicitudRepuesto> findAll() {
        return repo.findAll();
    }

    // 🔹 Listar paginadamente
    @Override
    public Page<SolicitudRepuesto> listaPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    // 🔹 Generar reporte PDF
    @Override
    public byte[] generateReport() throws JRException, SQLException, IOException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("txt_title", "Reporte Solicitudes de Repuestos");

        File jrxmlFile = new ClassPathResource("/reports/solicitudes_repuestos.jrxml").getFile();
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getPath());

        JasperPrint jprint = JasperFillManager.fillReport(jasperReport, param, dataSource.getConnection());
        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jprint);

        // Guardar copia local
        String outputPath = System.getProperty("user.dir") + "/solicitudes_repuestos.pdf";
        JasperExportManager.exportReportToPdfFile(jprint, outputPath);

        return pdfBytes;
    }

    // ✅ Aprobar solicitud
    public void aprobar(Long id) {
        SolicitudRepuesto solicitud = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.setEstado("Aprobado");
        repo.save(solicitud);
    }

    // ✅ Rechazar solicitud
    public void rechazar(Long id) {
        SolicitudRepuesto solicitud = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        solicitud.setEstado("Rechazado");
        repo.save(solicitud);
    }
    @Override
    public SolicitudRepuestoReport getSolicitudDetalle(Long id) {
        SolicitudRepuesto solicitud = iSolicitudRepuestoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        return SolicitudRepuestoReport.builder()
                .idSolicitud(solicitud.getIdSolicitud())
                .descripcionDeFalla(solicitud.getDescripcionDeFalla())
                .estado(solicitud.getEstado())
                .fechaRegistro(solicitud.getFechaRegistro())
                .observacionRevision(solicitud.getObservacionRevision())
                .usuario(UsuarioDTO.builder()
                        .idUsuario(solicitud.getUsuario().getIdUsuario())
                        .user(solicitud.getUsuario().getUser())
                        .estado(solicitud.getUsuario().getEstado())
                        .build())
                .bus(BusDTO.builder()
                        .idbus(solicitud.getBus().getIdbus())
                        .placa(solicitud.getBus().getPlaca())
                        .numeroIdentificador(solicitud.getBus().getNumeroIdentificador())
                        .modelo(solicitud.getBus().getModelo())
                        .capacidad(solicitud.getBus().getCapacidad())
                        .estado(solicitud.getBus().getEstado())
                        .fechaAdquisicion(solicitud.getBus().getFechaAdquisicion().toString())
                        .ultimoMantenimiento(solicitud.getBus().getUltimoMantenimiento())
                        .build())
                .detalleRepuestos(solicitud.getDetalleRepuestos().stream().map(rep -> DetalleRepuestoDTO.builder()
                        .id(rep.getId())
                        .idRepuesto(rep.getRepuesto().getIdRepuestos())
                        .nombreRepuesto(rep.getRepuesto().getNombreRepuesto())
                        .cantidad(rep.getCantidad())
                        .build()).toList())
                .detalleHerramientas(solicitud.getDetalleHerramientas().stream().map(her -> DetalleHerramientaDTO.builder()
                        .id(her.getId())
                        .idHerramienta(her.getHerramienta().getIdHerramientas())
                        .nombreHerramienta(her.getHerramienta().getNombreHerramienta())
                        .cantidad(her.getCantidad())
                        .build()).toList())
                .build();
    }

    @Override
    public void actualizarEstado(SolicitudEstadoDTO dto) {
        SolicitudRepuesto solicitud = iSolicitudRepuestoRepository.findById(dto.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado(dto.getEstado());

        if (dto.getObservacionRevision() != null) {
            solicitud.setObservacionRevision(dto.getObservacionRevision()); // ← aquí se guarda el motivo
        }

        iSolicitudRepuestoRepository.save(solicitud);
    }




}
