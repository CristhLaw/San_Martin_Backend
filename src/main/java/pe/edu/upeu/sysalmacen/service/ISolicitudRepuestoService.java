package pe.edu.upeu.sysalmacen.service;



import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upeu.sysalmacen.dtos.SolicitudEstadoDTO;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoDTO;
import pe.edu.upeu.sysalmacen.dtos.SolicitudRepuestoReport;
import pe.edu.upeu.sysalmacen.model.SolicitudRepuesto;

import java.io.IOException;
import java.sql.SQLException;

public interface ISolicitudRepuestoService extends ICrudGenericoService<SolicitudRepuesto, Long>{
    SolicitudRepuestoDTO saveD(SolicitudRepuestoDTO.SolicitudRepuestoCADto dto);
    SolicitudRepuestoDTO updateD(SolicitudRepuestoDTO.SolicitudRepuestoCADto dto, Long id);
    SolicitudRepuestoReport getSolicitudDetalle(Long id);


    byte[] generateReport() throws JRException, SQLException, IOException;

    Page<SolicitudRepuesto> listaPage(Pageable pageable);

    void actualizarEstado(SolicitudEstadoDTO dto);




}
