package br.com.sigvet.sigvetapi.feature.reports.usecases;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MonthlyAnimalCreationCountExcelUseCase {
    
    private final FetchMonthlyAnimalCreationCountUseCase fetchMonthlyAnimalCreationCountUseCase;


    private String getMonth(int position) {
        return switch (position) {
            case 1 -> "Janeiro";
            case 2 -> "Fevereiro";
            case 3 -> "Março";
            case 4 -> "Abril";
            case 5 -> "Maio";
            case 6 -> "Junho";
            case 7 -> "Julho";
            case 8 -> "Agosto";
            case 9 -> "Setembro";
            case 10 -> "Outubro";
            case 11 -> "Novembro";
            case 12 -> "Dezembro";
            default -> throw new IllegalArgumentException("Posição inválida para um mês: " + position);
        };
    }
    

    public byte[] execute() {
        try (final var workbook = new HSSFWorkbook()){
            final var content = fetchMonthlyAnimalCreationCountUseCase.execute();
            final var sheet = workbook.createSheet("Monthly Animal Creation Count");
            final var titleRow = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            titleRow.createCell(0).setCellValue("Mês");
            titleRow.createCell(1).setCellValue("Quantidade");
            titleRow.getCell(0).setCellStyle(headerStyle);
            titleRow.getCell(1).setCellStyle(headerStyle);

            int rowIndex = 1;

            for (final var element: content) {
                final var row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(getMonth(element.month()));
                row.createCell(1).setCellValue(element.count());
            }

            final var byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception error) {
            throw new ApplicationException("Monthly Animal Report Error", List.of(error.getMessage()));
        }
    }


}
