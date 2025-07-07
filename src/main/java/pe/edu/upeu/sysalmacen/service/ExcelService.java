package pe.edu.upeu.sysalmacen.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.repository.IRepuestosRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final IRepuestosRepository repuestosRepository;

    public void importarRepuestosDesdeExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Saltar encabezado

            try {
                Repuestos r = new Repuestos();
                r.setNombreRepuesto(getStringValue(row.getCell(0)));
                r.setDescripcion(getStringValue(row.getCell(1)));

                // Validación segura de cantidad
                double cantidadVal = 0;
                Cell cantidadCell = row.getCell(2);
                if (cantidadCell != null) {
                    switch (cantidadCell.getCellType()) {
                        case NUMERIC -> cantidadVal = cantidadCell.getNumericCellValue();
                        case STRING -> {
                            try {
                                cantidadVal = Double.parseDouble(cantidadCell.getStringCellValue().replaceAll("[^\\d.]", ""));
                            } catch (NumberFormatException e) {
                                cantidadVal = 0;
                            }
                        }
                        case FORMULA -> {
                            try {
                                cantidadVal = cantidadCell.getNumericCellValue();
                            } catch (Exception e) {
                                cantidadVal = 0;
                            }
                        }
                        default -> cantidadVal = 0;
                    }
                }

                r.setCantidad((int) cantidadVal);
                r.setMarca(getStringValue(row.getCell(3)));
                r.setCodigoFabricante(getStringValue(row.getCell(4)));
                r.setUnidadMedida(getStringValue(row.getCell(5)));


                if (!repuestosRepository.existsByNombreRepuestoAndCodigoFabricante(
                        r.getNombreRepuesto(), r.getCodigoFabricante())) {
                    repuestosRepository.save(r);
                }

            } catch (Exception ex) {
                System.err.println("Error al procesar fila " + row.getRowNum() + ": " + ex.getMessage());
            }
        }

        workbook.close();
    }

    public void exportarRepuestosAExcel(OutputStream out) throws IOException {
        List<Repuestos> lista = repuestosRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Repuestos");

        // Encabezado
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("nombre repuesto");
        header.createCell(1).setCellValue("descripcion");
        header.createCell(2).setCellValue("cantidad");
        header.createCell(3).setCellValue("marca");
        header.createCell(4).setCellValue("codigo fabricante");
        header.createCell(5).setCellValue("unidad medida");

        // Cuerpo de la tabla
        int i = 1;
        for (Repuestos r : lista) {
            Row row = sheet.createRow(i++);
            row.createCell(0).setCellValue(r.getNombreRepuesto());
            row.createCell(1).setCellValue(r.getDescripcion());
            row.createCell(2).setCellValue(r.getCantidad() != null ? r.getCantidad() : 0);
            row.createCell(3).setCellValue(r.getMarca());
            row.createCell(4).setCellValue(r.getCodigoFabricante());
            row.createCell(5).setCellValue(r.getUnidadMedida());
        }

        workbook.write(out);
        workbook.close();
    }

    private String getStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
