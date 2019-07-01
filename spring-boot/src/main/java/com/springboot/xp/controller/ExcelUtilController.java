package com.springboot.xp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.xp.utils.ExcelReader;
import com.springboot.xp.utils.ExcelType;

@RestController
@RequestMapping("excel")
public class ExcelUtilController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ExcelUtilController.class);

    private static String template = "INSERT INTO `T_TABEL` (`ID`, `CREATE_TIME`, `GOOD_NAME`, "
            + "`AMOUNT`, `PRICE`, `DELIVER_TIME`, `USER_NAME`, `USER_PHONE`) VALUES \n";

    @RequestMapping("gen/sql")
    public void readExcelGenSqlFile(@RequestParam(name = "file") MultipartFile file, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e1) {
            logger.error("errorMessage[{}]", e1.getMessage(), e1);
        }
        ExcelReader reader = null;
        try {
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),
                    file.getOriginalFilename().length() - 1);
            reader = new ExcelReader(inputStream, fileType.equalsIgnoreCase("xlsx") ? ExcelType.XLSX : ExcelType.XLS,
                    false);
        } catch (IOException e) {
            logger.error("errorMessage[{}]", e.getMessage(), e);
        }
        Sheet sheetAt = reader.getSheetAt(0);
        Row row = null;
        StringBuilder values = new StringBuilder();
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {
            row = sheetAt.getRow(i);
            if (null == row || row.getLastCellNum() <= 0) {
                continue;
            }
            StringBuilder value = new StringBuilder().append("(");
            Cell cell = null;
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == (row.getLastCellNum() - 1)) {
                    value.append("'" + getCellValue(cell) + "')").append(i == (sheetAt.getLastRowNum()) ? ";" : ",");
                } else {
                    value.append("'" + getCellValue(cell) + "', ");
                }
            }
            values.append(value.toString()).append("\n");
        }
        String sql = template + values.toString();
        try {
            outputFile(response, "values" + System.currentTimeMillis() + ".sql", sql.getBytes());
        } catch (UnsupportedEncodingException e) {
            logger.error("sql=[{}]", sql);
            logger.error("errorMessage[{}]", e.getMessage(), e);
        }
    }

    private static String getCellValue(Cell cell) {
        if (null == cell) {
            return "0";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue()).trim();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                return DateFormatUtils.format(date, "yyyy/MM/dd");
            } else {
                DecimalFormat df = new DecimalFormat("0.00");
                return df.format(cell.getNumericCellValue());
            }
        } else {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return String.valueOf(cell.getStringCellValue()).trim();
        }
    }
}
