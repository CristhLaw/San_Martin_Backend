package pe.edu.upeu.sysalmacen.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.sysalmacen.service.ExcelService;

import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // ⬅️ Necesario si Angular corre ahí
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/importar-repuestos")
    public ResponseEntity<?> importarRepuestos(@RequestParam("file") MultipartFile file) {
        try {
            excelService.importarRepuestosDesdeExcel(file);
            return ResponseEntity.ok("✅ Repuestos importados correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error al procesar el archivo Excel");
        }
    }

    @GetMapping("/exportar-repuestos")
    public void exportarRepuestos(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"repuestos.xlsx\"");

        try (OutputStream out = response.getOutputStream()) {
            excelService.exportarRepuestosAExcel(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "❌ Error al exportar Excel");
        }
    }
}
