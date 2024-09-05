/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.renafipse.mks.capas.reportes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author renafipse2
 */
public class ExcelReporte {

    private static final Pattern patternFecha = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
    private static final Pattern patternFechaHora = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/]?(0[1-9]|1[0-2])[/]?(18|19|20|21)\\\\d{2} ([0-2][0-4]:[0-5][0-9]:[0-5][0-9])$");
    private static final Pattern patternNumero = Pattern.compile("^[+-]?(\\d*\\.)?\\d+$");
    private HSSFWorkbook workbook;
    private Map<String, CellStyle> styles;
    private HashMap<String, HSSFColor> palettes;

    public ExcelReporte() {
    }

    public void processXLS_Reporteria(Object document, String nombreExcel) {
        workbook = (HSSFWorkbook) document;
        HSSFSheet sheet = workbook.createSheet(nombreExcel);
        HSSFRow row;
        celdaEstilo();
        HSSFSheet st = workbook.getSheetAt(0);
        int pnr = st.getPhysicalNumberOfRows();
        for (int i = 0; i < pnr; i++) {
            HSSFRow rowAux = st.getRow(i);
            int pnc = rowAux.getPhysicalNumberOfCells();
            row = sheet.createRow(i + 2);
            for (int j = 0; j < pnc; j++) {
                if (i == 0) {//Solo titulos
                    sheet.setColumnWidth(j + 1, (rowAux.getCell(j).toString().length() + 7) * 280);
                } else {
                    int widthColumn;
                    try {
                        widthColumn = (rowAux.getCell(j).toString().length() + 5) * 256;
                    } catch (NullPointerException npe) {
                        widthColumn = 256;
                    }
                    if (sheet.getColumnWidth(j + 1) < widthColumn) {
                        sheet.setColumnWidth(j + 1, widthColumn);
                    }
                }
                try {
                    try {
                        crearCelda(row, j + 1, rowAux.getCell(j).toString());
                    } catch (NullPointerException npe) {
                        crearCelda(row, j + 1, " ");
                    }
                } catch (ParseException ex) {
                    System.out.println("Error en Expreciones Regulares para formato de celdas");
                }
            }
        }
        workbook.removeSheetAt(0);
    }

    private void crearCelda(HSSFRow fila, int columna, String valor) throws ParseException {
        HSSFCell cell = fila.createCell(columna);

        //TITULOS
        if (fila.getRowNum() == 2) {
            fila.setHeightInPoints(40);
            valor = valor.replace("_", " ");
            cell.setCellValue(new HSSFRichTextString(valor));
            cell.setCellStyle(styles.get("titulo"));
        } //DATOS
        else {
            fila.setHeightInPoints(20);
            //fecha
            if (patternFecha.matcher(valor).matches()) {
                SimpleDateFormat formatoFechas = new SimpleDateFormat("dd/mm/yyyy");
                cell.setCellValue(formatoFechas.parse(valor));
                cell.setCellStyle((fila.getRowNum() % 2 == 0) ? styles.get("fechap") : styles.get("fechai"));
            }//string
            else if (" ".equals(valor) || !patternNumero.matcher(valor).matches() || (valor.length() > 1 && (valor.charAt(0)) == '0' && (valor.charAt(1)) != '.') || (valor.length() > 9 && (valor.charAt(0)) == '1' && !valor.contains("."))) {
                cell.setCellValue(new HSSFRichTextString(valor));
                //cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle((fila.getRowNum() % 2 == 0) ? styles.get("stringp") : styles.get("stringi"));
            } else {//decimal y Entero
                //if (valor.contains(".")) {
                    //DecimalFormat dFormat = new DecimalFormat("##.##");
                    //valor = new BigDecimal(valor).setScale(2).toString();
                    cell.setCellValue(new HSSFRichTextString(valor));
                    cell.setCellStyle((fila.getRowNum() % 2 == 0) ? styles.get("decimalp") : styles.get("decimali"));
                //}//entero 
               // else {
                    cell.setCellValue(new HSSFRichTextString(valor));
                    cell.setCellStyle((fila.getRowNum() % 2 == 0) ? styles.get("enterop") : styles.get("enteroi"));
                    
               // }
            }
        }
    }

    private void celdaEstilo() {
        styles = new HashMap<String, CellStyle>();
        CellStyle cellStyle;
        Font fontStyle;
        HSSFDataFormat format;
        colores();
        ///////////////////      TITULO      /////////////////////////////
        cellStyle = workbook.createCellStyle();
        //Alineacion de celda
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("titulo").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 12);
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fontStyle.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(fontStyle);
        styles.put("titulo", cellStyle);

        ///////////////////      DATOS STRING     /////////////////////////////
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosi").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        styles.put("stringi", cellStyle);
        
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosp").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        styles.put("stringp", cellStyle);
        ///////////////////      DATOS FECHA     /////////////////////////////
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosi").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        // formato de fechas
        format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("dd/mm/yyyy"));
        styles.put("fechai", cellStyle);
        
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosp").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        // formato de fechas
        format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("dd/mm/yyyy"));
        styles.put("fechap", cellStyle);
        ///////////////////      DATOS DECIMAL     /////////////////////////////
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosi").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        //formato patron
        format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("#,##0.00"));
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styles.put("decimali", cellStyle);
        
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosp").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);

        format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("#,##0.00"));
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styles.put("decimalp", cellStyle);
        ///////////////////      DATOS ENTERO     /////////////////////////////
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosi").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        //formato patron
        //format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("#"));
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styles.put("enteroi", cellStyle);
        
        cellStyle = workbook.createCellStyle();
        //Color de fondo de celda
        cellStyle.setFillForegroundColor(palettes.get("datosp").getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //Color y estilo de los bordes de celda 
        bordeCelda(cellStyle);
        // Estilo de le texto de celda
        fontStyle = workbook.createFont();
        fontStyle.setFontHeightInPoints((short) 10);
        fontStyle.setColor(palettes.get("titulo").getIndex());
        cellStyle.setFont(fontStyle);
        //formato patron
        //format = workbook.createDataFormat();
        //cellStyle.setDataFormat(format.getFormat("#"));
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styles.put("enterop", cellStyle);
    }

    private void colores() {
        palettes = new HashMap<String, HSSFColor>();
        HSSFPalette palette = workbook.getCustomPalette();
        /////Color Fondo titulo //////////
        HSSFColor hssfColor = palette.findColor((byte) 73, (byte) 69, (byte) 41);
        if (hssfColor == null) {
            palette.setColorAtIndex(HSSFColor.LAVENDER.index, (byte) 73, (byte) 69, (byte) 41);
            hssfColor = palette.getColor(HSSFColor.LAVENDER.index);
        }
        palettes.put("titulo", hssfColor);
        /////Color Fondo Datos Impares //////////
        hssfColor = palette.findColor((byte) 229, (byte) 237, (byte) 211);
        if (hssfColor == null) {
            palette.setColorAtIndex(HSSFColor.LEMON_CHIFFON.index, (byte) 229, (byte) 237, (byte) 211);
            hssfColor = palette.getColor(HSSFColor.LEMON_CHIFFON.index);
        }
        palettes.put("datosi", hssfColor);
        /////Color Fondo Datos Pares //////////
        hssfColor = palette.findColor((byte) 221, (byte) 217, (byte) 196);
        if (hssfColor == null) {
            palette.setColorAtIndex(HSSFColor.CORAL.index, (byte) 221, (byte) 217, (byte) 196);
            hssfColor = palette.getColor(HSSFColor.CORAL.index);
        }
        palettes.put("datosp", hssfColor);
    }

    private void bordeCelda(CellStyle cellStyle) {
        //Color de bordes de celda
        cellStyle.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.WHITE.getIndex());
        cellStyle.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        cellStyle.setTopBorderColor(IndexedColors.WHITE.getIndex());
        //estilo de bordes de celda
        cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
    }
}
