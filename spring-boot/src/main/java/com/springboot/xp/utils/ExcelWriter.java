package com.springboot.xp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelWriter {

    private static final Logger logger = LoggerFactory.getLogger(ExcelWriter.class);

    /**
     * excel的类型
     */
    private ExcelType excelType;

    /**
     * POI的处理Excel的对象
     */
    private Workbook workbook;

    /**
     * 构造函数
     * 
     * @param originalInputStream 原始Excel的流数据，若是null，则标示创建新的Excel
     * @param excelType excel的类型
     * @throws IOException
     */
    public ExcelWriter(InputStream originalInputStream, ExcelType excelType) throws IOException {
        try {
            this.excelType = excelType;
            if (excelType == ExcelType.XLS) {
                this.workbook = originalInputStream == null ? new HSSFWorkbook()
                        : new HSSFWorkbook(originalInputStream);
            } else {
                this.workbook = originalInputStream == null ? new XSSFWorkbook()
                        : new XSSFWorkbook(originalInputStream);
            }
        } finally {
            IOUtils.closeQuietly(originalInputStream);
        }
    }

    public boolean checkSheetExist(String sheetName) {
        if (null == sheetName) {
            return false;
        }
        if (workbook.getSheet(sheetName) != null) {
            return true;
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 创建sheet
     * 
     * Except the 31-character constraint, Excel applies some other rules:
     * <p>
     * Sheet name MUST be unique in the workbook and MUST NOT contain the any of the following characters:
     * <ul>
     * <li>0x0000</li>
     * <li>0x0003</li>
     * <li>colon (:)</li>
     * <li>backslash (\)</li>
     * <li>asterisk (*)</li>
     * <li>question mark (?)</li>
     * <li>forward slash (/)</li>
     * <li>opening square bracket ([)</li>
     * <li>closing square bracket (])</li>
     * </ul>
     * The string MUST NOT begin or end with the single quote (') character.
     * 
     * @param sheetName
     * @return sheet的index
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int createSheet(String sheetName) {
        Sheet sheet = workbook.createSheet(sheetName);
        return workbook.getSheetIndex(sheet);
    }

    /**
     * 
     * 功能描述: <br>
     * 添加行
     * 
     * @param sheetNum 添加的行所在的sheet号
     * @param rowNum 添加的行号
     * @param cellValueList 添加的cell内容; 只支持五种类型:<br>
     *            {@code java.lang.String}<br>
     *            {@code java.lang.Boolean}<br>
     *            {@code java.lang.Double}<br>
     *            {@code java.util.Date}<br>
     *            {@code java.util.Calendar}
     * @return 添加成功或者失败
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean addRow(int sheetNum, int rowNum, List<Object> cellValueList) {
        if (sheetNum < 0) {
            logger.error("SheetNum[{}] is invalid.", sheetNum);
            return false;
        }
        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("sheet[{}] is not exist.", sheetNum);
            return false;
        }
        return addRow(sheet, rowNum, cellValueList);
    }

    /**
     * 
     * 功能描述: <br>
     * 添加行
     * 
     * @param sheetName 添加的行所在的sheet名字
     * @param rowNum 添加的行号
     * @param cellValueList 添加的cell内容; 只支持五种类型:<br>
     *            {@code java.lang.String}<br>
     *            {@code java.lang.Boolean}<br>
     *            {@code java.lang.Double}<br>
     *            {@code java.util.Date}<br>
     *            {@code java.util.Calendar}
     * @return 添加成功或者失败
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean addRow(String sheetName, int rowNum, List<Object> cellValueList) {
        if (sheetName == null) {
            logger.error("sheetName[{}] is null.", sheetName);
            return false;
        }
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            logger.error("sheet[{}] is not exist.", sheetName);
            return false;
        }
        return addRow(sheet, rowNum, cellValueList);
    }

    private boolean addRow(Sheet sheet, int rowNum, List<Object> cellValueList) {
        if (rowNum < 0) {
            logger.error("RowNum[{}] is invalid.", rowNum);
            return false;
        }
        if (cellValueList == null || cellValueList.size() <= 0) {
            logger.error("CellValueList is empty.");
            return false;
        }
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < cellValueList.size(); i++) {
            Cell cell = row.createCell(i);

            Object cellValue = cellValueList.get(i);
            if (cellValue instanceof String) {
                cell.setCellValue((String) cellValue);
            } else if (cellValue instanceof Double) {
                cell.setCellValue((Double) cellValue);
            } else if (cellValue instanceof Integer) {
                cell.setCellValue((Integer) cellValue);
            } else if (cellValue instanceof Long) {
                cell.setCellValue((Long) cellValue);
            } else if (cellValue instanceof Boolean) {
                cell.setCellValue((Boolean) cellValue);
            } else if (cellValue instanceof Calendar) {
                cell.setCellValue((Calendar) cellValue);
            } else if (cellValue instanceof Date) {
                cell.setCellValue((Date) cellValue);
            } else {
                logger.error("Type[{}] is Unsupported at cell({},{})", cellValue.getClass().getName(), rowNum, cell);
                cell.setCellValue("Unsupported Type");
            }
        }
        return true;
    }

    private Sheet getSheetAt(int sheetNum) {
        try {
            return workbook.getSheetAt(sheetNum);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    // public void setCellFormula(String sheetName, int rownum, int cellnum,
    // String formula) {
    // if (sheetName == null) {
    // return;
    // }
    // if (formula == null || formula.equals("")) {
    // return;
    // }
    // Asserts.notNull(workbook, "Excel");
    // Sheet sheet = workbook.getSheet(sheetName);
    // Asserts.notNull(sheet, "sheet[" + sheetName + "]");
    // Row row = sheet.getRow(rownum);
    // if (row != null) {
    // Cell cell = row.getCell(cellnum);
    // if (cell != null) {
    // cell.setCellFormula(formula);
    // }
    // }
    // }

    public void flush(OutputStream outputStream) throws IOException {
        this.workbook.write(outputStream);
    }

    public ExcelType getExcelType() {
        return excelType;
    }
}
