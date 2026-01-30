package eu.senla.management.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReadFromExcelOrCsvFile<T> {

    private final Class<T> type;

    public ReadFromExcelOrCsvFile(Class<T> type) {
        this.type = type;
    }

    //Чтение csv
    public List<T> readCsv(String path) throws Exception {
        List<T> result = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String headerLine = br.readLine();
            if (headerLine == null) return result;

            String[] headers = headerLine.split(";");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                result.add(mapRow(headers, values));
            }
        }
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    //Чтение xlsx
    public List<T> readExcel(String path) throws Exception {
        List<T> result = new ArrayList<>();
        try (InputStream is = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            //Заголовки
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            DataFormatter formatter = new DataFormatter(); // ключевой момент для чисел
            //Данные
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String[] values = new String[headers.size()];
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    values[i] = (cell != null) ? formatter.formatCellValue(cell) : "";
                }
                result.add(mapRow(headers.toArray(new String[0]), values));
            }
        }
        return result;
    }


    
    
    private T mapRow(String[] headers, String[] values) throws Exception {
        T obj = type.getDeclaredConstructor().newInstance();

//        for (int i = 0; i < headers.length; i++) {
//            String fieldName = headers[i].trim();
//            Field field = type.getDeclaredField(fieldName);
//            field.setAccessible(true);
//
        for (Field field : type.getDeclaredFields()) {
            Column col = field.getAnnotation(Column.class);
            if (col == null) continue;

            String columnName = col.value();
            int idx = Arrays.asList(headers).indexOf(columnName);
            if (idx == -1) continue;

            field.setAccessible(true);
            String raw = values[idx];
            if (raw == null) raw = ""; // защита от null;

            if (field.getType() == int.class || field.getType() == Integer.class) {
                field.set(obj, raw.isEmpty() ? 0 : Integer.parseInt(raw));
            }else if (field.getType() == long.class || field.getType() == Long.class) {
                field.set(obj, raw.isEmpty() ? 0L : Long.parseLong(raw));
            }
            else if (field.getType() == double.class || field.getType() == Double.class) {
                field.set(obj, raw.isEmpty() ? 0.0 : Double.parseDouble(raw));
            } else field.set(obj, raw);
        }


        return obj;
    }


    
}
