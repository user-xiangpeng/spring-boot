package com.springboot.xp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Excel的阅读器
 * 
 * @author wujiajun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExcelReader {

    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    /**
     * excel的类型
     */
    private ExcelType excelType;

    /**
     * 是否保护包含宏函数的node
     */
    private boolean preserveNodes;

    /**
     * POI的处理Excel的对象
     */
    private Workbook workbook;

    /**
     * 构造函数
     * 
     * @param inputStream excel文件输入流
     * @param excelType excel类型
     * @param preserveNodes 是否保护包含宏函数的node，只对{@code ExcelType.XLS}有效
     * @throws IOException
     */
    public ExcelReader(InputStream inputStream, ExcelType excelType, boolean preserveNodes) throws IOException {
        try {
            this.excelType = excelType;
            this.preserveNodes = preserveNodes;
            if (excelType == ExcelType.XLS) {
                this.workbook = new HSSFWorkbook(inputStream, preserveNodes);
            } else {
                this.workbook = new XSSFWorkbook(inputStream);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public Sheet getSheetAt(int sheetNum) {
        try {
            return workbook.getSheetAt(sheetNum);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    /**
     * 
     * 功能描述: <br>
     * 获取sheet下最后一行的行号
     * 
     * @param sheetNum sheet号
     * @return last row contained n this sheet (0-based),sheet不存在返回-1
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getLastRowNum(int sheetNum) {
        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return -1;
        }
        return sheet.getLastRowNum();
    }

    /**
     * 
     * 功能描述: <br>
     * 获取cell的内容，无论什么类型，都转为String
     * 
     * @param sheetNum sheet的序号，从0开始
     * @param rowNum 行号，从0开始
     * @param cellNum 列号，从0开始
     * @return cell的内容
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getCellValue(int sheetNum, int rowNum, int cellNum) {
        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return null;
        }
        Row row = sheet.getRow(rowNum);
        if (null == row) {
            logger.error("Row[{}] in Sheet[{}] is not exist.", rowNum, sheetNum);
            return null;
        }

        Cell cell = row.getCell(cellNum);
        if (null == cell) {
            logger.error("Cell[{}] in the Row[{}] in the Sheet[{}] is not exist.", cellNum, rowNum, sheetNum);
            return null;
        }

        return getCellValue(cell);
    }

    /**
     * 
     * 功能描述: <br>
     * 获取多行多列的cell的内容，无论什么类型，都转为String
     * 
     * @param sheetNum sheet的序号，从0开始
     * @param startRowNum 开始的行数，从0开始
     * @param endRowNum 结束的行数（包含），从0开始
     * @param startCellNum 开始的列数，从0开始
     * @param endCellNum 结束的列数（包含），从0开始
     * @return 返回矩阵型的内容，若row和cell有异常，则对应的value为null
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<List<String>> getCellValues(int sheetNum, int startRowNum, int endRowNum, int startCellNum,
            int endCellNum) {
        if (sheetNum < 0) {
            logger.error("SheetNum[{}] is invalid.", sheetNum);
            return Collections.emptyList();
        }
        if (startRowNum < 0 || endRowNum < 0 || startRowNum > endRowNum) {
            logger.error("StartRowNum[{}] and EndRowNum[{}] are invalid.", startRowNum, endRowNum);
            return Collections.emptyList();
        }
        if (startCellNum < 0 || endCellNum < 0 || startCellNum > endCellNum) {
            logger.error("StartCellNum[{}] and EndCellNum[{}] are invalid.", startCellNum, endCellNum);
            return Collections.emptyList();
        }

        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return Collections.emptyList();
        }

        List<List<String>> values = new ArrayList<List<String>>();

        // 循环row
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            List<String> cellValues = new ArrayList<String>();

            Row row = sheet.getRow(rowNum);
            int thisEndCellNum = (row != null && endCellNum > row.getLastCellNum()) ? row.getLastCellNum() : endCellNum;
            // 循环列Cell
            for (int cellNum = startCellNum; cellNum <= thisEndCellNum; cellNum++) {
                cellValues.add(getCellValue(row, cellNum));
            }

            values.add(cellValues);
        }

        return values;
    }

    /**
     * 
     * 功能描述: <br>
     * 获取多行一列的cell的内容，无论什么类型，都转为String
     * 
     * @param sheetNum sheet的序号，从0开始
     * @param startRowNum 开始的行数，从0开始
     * @param endRowNum 结束的行数（包含），从0开始
     * @param cellNum 列数，从0开始
     * @return 返回列表，若row和cell有异常，则对应的value为null
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<String> getCellValuesBySingleCell(int sheetNum, int startRowNum, int endRowNum, int cellNum) {
        if (sheetNum < 0) {
            logger.error("SheetNum[{}] is invalid.", sheetNum);
            return Collections.emptyList();
        }
        if (startRowNum < 0 || endRowNum < 0 || startRowNum > endRowNum) {
            logger.error("StartRowNum[{}] and EndRowNum[{}] are invalid.", startRowNum, endRowNum);
            return Collections.emptyList();
        }
        if (cellNum < 0) {
            logger.error("CellNum[{}] is invalid.", cellNum);
            return Collections.emptyList();
        }

        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return Collections.emptyList();
        }

        List<String> values = new ArrayList<String>();

        // 循环row
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            values.add(getCellValue(row, cellNum));
        }

        return values;
    }

    /**
     * 
     * 功能描述: <br>
     * 获取一行多列的cell的内容，无论什么类型，都转为String
     * 
     * @param sheetNum sheet的序号，从0开始
     * @param rowNum 行数，从0开始
     * @param startCellNum 开始的列数，从0开始
     * @param endCellNum 结束的列数（包含），从0开始
     * @return 返回列表，若row和cell有异常，则对应的value为null
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<String> getCellValuesBySingleRow(int sheetNum, int rowNum, int startCellNum, int endCellNum) {
        if (sheetNum < 0) {
            logger.error("SheetNum[{}] is invalid.", sheetNum);
            return Collections.emptyList();
        }
        if (rowNum < 0) {
            logger.error("RowNum[{}] is invalid.", rowNum);
            return Collections.emptyList();
        }
        if (startCellNum < 0 || endCellNum < 0 || startCellNum > endCellNum) {
            logger.error("StartCellNum[{}] and EndCellNum[{}] are invalid.", startCellNum, endCellNum);
            return Collections.emptyList();
        }

        Sheet sheet = getSheetAt(sheetNum);
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return Collections.emptyList();
        }

        List<String> values = new ArrayList<String>();

        Row row = sheet.getRow(rowNum);
        // 循环列Cell
        for (int cellNum = startCellNum; cellNum <= endCellNum; cellNum++) {
            values.add(getCellValue(row, cellNum));
        }

        return values;
    }

    private String getCellValue(Row row, int cellNum) {
        if (row == null) {
            return null;
        } else {
            Cell cell = row.getCell(cellNum);
            if (cell == null) {
                return null;
            } else {
                return getCellValue(cell);
            }
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue()).trim();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
            String value = String.valueOf(cell.getNumericCellValue()).trim();
            // if (value.contains(".")) {
            // return value.substring(0, value.lastIndexOf("."));
            // } else {
            return value;
            // }
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }

    public ExcelType getExcelType() {
        return excelType;
    }

    public boolean isPreserveNodes() {
        return preserveNodes;
    }

    /**
     * 
     * 功能描述: <br>
     * 获取指定行数且指定列下标的行数据
     * 
     * @param sheetNum 页
     * @param startRowNum 开始行
     * @param endRowNum 结束行
     * @param cellIndexs 指定的一批下标
     * @return 指定行数且指定列下标的所有行数据
     */
    public List<List<String>> getCellValuesByCellIndexs(int sheetNum, int startRowNum, int endRowNum,
            List<Integer> cellIndexs) {
        if (CollectionUtils.isEmpty(cellIndexs)) {
            return Collections.emptyList();
        }
        if (startRowNum < 0 || endRowNum < 0 || startRowNum > endRowNum) {
            logger.error("StartRowNum[{}] and EndRowNum[{}] are invalid.", startRowNum, endRowNum);
            return Collections.emptyList();
        }
        Sheet sheet = getSheetAt(sheetNum);
        int lastRowNum = getLastRowNum(sheetNum);
        endRowNum = endRowNum > lastRowNum ? lastRowNum : endRowNum;
        if (sheet == null) {
            logger.error("Sheet[{}] is not exist.", sheetNum);
            return Collections.emptyList();
        }
        // 获得页数
        List<List<String>> rowValues = new ArrayList<List<String>>();
        List<String> cellValues = null;
        for (int rowNum = startRowNum; rowNum <= endRowNum; rowNum++) {
            cellValues = new ArrayList<String>();
            Row row = sheet.getRow(rowNum);
            for (Integer index : cellIndexs) {
                if (null == row.getCell(index)) {
                    cellValues.add(StringUtils.EMPTY);
                } else {
                    cellValues.add(getCellValue(row.getCell(index)));
                }
            }
            rowValues.add(cellValues);
        }
        return rowValues;
    }

}
