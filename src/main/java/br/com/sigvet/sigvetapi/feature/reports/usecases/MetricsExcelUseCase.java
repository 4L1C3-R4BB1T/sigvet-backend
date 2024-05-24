package br.com.sigvet.sigvetapi.feature.reports.usecases;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MetricsExcelUseCase {
    
    private final FetchGeneralMetricsUseCase fetchGeneralMetricsUseCase;

    public byte[] execute() {
        try(final var workbook = new HSSFWorkbook()) {
            final var generalMetrics = fetchGeneralMetricsUseCase.execute();
            final var sheet = workbook.createSheet("General Metrics");
            final var row = sheet.createRow(0);

            row.createCell(0).setCellValue("Total de Clientes (Mês Atual)");
            row.createCell(1).setCellValue("Total de Clientes (Mês Anterior)");
            row.createCell(2).setCellValue("Total de Animais (Mês Atual)");
            row.createCell(3).setCellValue("Total de Animais (Mês Anterior)");
            row.createCell(4).setCellValue("Total de Consultas (Mês Atual)");
            row.createCell(5).setCellValue("Total de Consultas (Mês Anterior)");
            row.createCell(6).setCellValue("Receita (Mês Atual)");
            row.createCell(7).setCellValue("Receita (Mês Anterior)");

            final var newRow = sheet.createRow(1);
            newRow.createCell(0).setCellValue(generalMetrics.totalClients());
            newRow.createCell(1).setCellValue(generalMetrics.totalClientsPreviousMonth());
            newRow.createCell(2).setCellValue(generalMetrics.totalAnimals());
            newRow.createCell(3).setCellValue(generalMetrics.totalAnimalsPreviousMonth());
            newRow.createCell(4).setCellValue(generalMetrics.totalConsults());
            newRow.createCell(5).setCellValue(generalMetrics.totalConsultsPreviousMonth());
            newRow.createCell(6).setCellValue(generalMetrics.revenue().doubleValue());
            newRow.createCell(7).setCellValue(generalMetrics.totalRevenuePreviousMonth().doubleValue());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception error) {
            throw new ApplicationException("Report Error", List.of(error.getMessage()));
        }
    }
}
