package com.melcrys.pnsMilage.generator;

import com.melcrys.pnsMilage.common.Location;
import com.melcrys.pnsMilage.common.Milage;
import com.melcrys.pnsMilage.common.Trip;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    private static List<Milage> milageList = new ArrayList<Milage>();
    private static final String EXCEL_FILE_PATH="D:\\Travel_%s.xlsx";
    private static final String pattern = "yyyy_MM_dd_HH_mm_ss_SSS";

    public static File generateDocument(Milage milage){
        //Timestamp for the filename
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        String fileName = String.format(EXCEL_FILE_PATH,date);

        //Do something
        Main writeExcel = new Main();
        writeExcel.writeExcelFile(fileName, milage);

        File fileToReturn = new File(fileName);
        return fileToReturn;
    }

    public static void main(String[] args) {
        //Setup
        Trip trip1 = new Trip(new Date(), Location.HONEA_PATH,"Resource Team");
        Trip trip2 = new Trip(new Date(),Location.WREN,"Resource Team");
        Trip trip3 = new Trip(new Date(),Location.CENTERVILLE,"Resource Team");
        Trip trip4 = new Trip(new Date(),Location.LAKESIDE,"Resource Team");
        Trip trip5 = new Trip(new Date(),Location.HARTWELL,"Resource Team");
        Trip trip6 = new Trip(new Date(),Location.CLEMSON,"Resource Team");
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        trips.add(trip5);
        trips.add(trip6);
        Milage milage = new Milage("Melvin","Cureton","12345",trips);

        generateDocument(milage);
    }

    public void writeExcelFile(String excelFilePath, Milage milage) {
        final String[] header= {"First & Last Name", "Employee # (not mash #)", "Total Number of Miles", "Date of Travel"
                , "Destination of Trip To/From  (RT)", "Purpose of Trip  (Tech/Staff Support, Meeting w/___, Lab Transport, Bank, etc)"
                , "Dept #"};
        Workbook workbook = null;
        // Excel with .xslx extension
        workbook = new XSSFWorkbook();
        // For .xls extension HSSF workbook can be created
        //workbook = new HSSFWorkbook();

        // Creating sheet with in the workbook
        Sheet sheet = workbook.createSheet("Sheet1");
        /*Font and style For Header*/
        Font font = workbook.createFont();
        font.setFontName("VERDANA");
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(true);

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        Row row = sheet.createRow(0);
        // Writing header to excel
        for(int i = 0; i < header.length; i++) {
            // each column 20 characters wide
            sheet.setColumnWidth(i, 20*256);
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
            cell.setCellStyle(style);
        }
        // Header styling ends
        //Preparing column data for each row
        CellStyle dateStyle = workbook.createCellStyle();
        // Setting format For the date column
        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat()
                .getFormat("dd/MM/yyyy"));
        int rowNum = 1;
        for(Trip trip : milage.getTrips()) {
            // create new row
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(milage.getfName() + " " + milage.getlName());
            row.createCell(1).setCellValue(milage.getEmployeeNumber());
            row.createCell(2).setCellValue(trip.getTravelLocation().getDistance().toString());

            Cell cell = row.createCell(3);
            cell.setCellValue(trip.getTravelDate());
            cell.setCellStyle(dateStyle);

            row.createCell(4).setCellValue("PNS to " + trip.getTravelLocation().getName());
            row.createCell(5).setCellValue(trip.getTravelPurpose());
            row.createCell(6).setCellValue(trip.getTravelLocation().getDept());
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(excelFilePath);
            // Writing to excel sheet
            workbook.write(outputStream);
        } catch (IOException exp) {
            // TODO Auto-generated catch block
            exp.printStackTrace();
        }finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                    workbook.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
