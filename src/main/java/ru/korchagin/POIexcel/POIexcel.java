package ru.korchagin.POIexcel;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

public class POIexcel {
    private static DataFormatter formatter = new DataFormatter();
    public static void main(String[] args) {
        familyStudent student = null;
        try {
            FileInputStream excelFile = new FileInputStream(new File("./tmp/MyExcel.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            System.out.println("Успешно!");

            // Игнорируем шапку.
            iterator.next();
            while(iterator.hasNext()) {
                XSSFRow currentRow = (XSSFRow) iterator.next();
                Iterator<Cell> cellIterator = currentRow.cellIterator();
                student = new familyStudent(
                        getCCV(cellIterator),   // id
                        getCCV(cellIterator),   // FIO
                        getCCV(cellIterator),   // SK
                        getCCV(cellIterator),   // room
                        getCCV(cellIterator),   // busyWith
                        getCCV(cellIterator),   // freeWith
                        getCCV(cellIterator),   // registration
                        getCCV(cellIterator),   // Date of Birth
                        getCCV(cellIterator),   // Place of Birth
                        getCCV(cellIterator),   // passportSeries
                        getCCV(cellIterator),   // passportNumber
                        getCCV(cellIterator),   // institute
                        getCCV(cellIterator),   // phoneNumber
                        getCCV(cellIterator)    // familyPersons - string
                );

                System.out.println(student.getBusyWith());
//                while (cellIterator.hasNext()) {
//
//
//                    Cell currentCell = cellIterator.next();
////                    System.out.println(currentCell.getStringCellValue());
//                    System.out.println(formatter.formatCellValue(currentCell));
//                }
            }
        } catch (FileNotFoundException e) {
            // Файл не найден
            e.printStackTrace();
        } catch (IOException e) {
            // Неизвестная ошибка
            e.printStackTrace();
        }

        System.out.println("Исходный файл успешно распознан");

        try {
//      1   https://www.programmersought.com/article/60567225/
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage("./tmp/familyStudentTemplate.docx"));
            List<XWPFParagraph> allParagraph = document.getParagraphs();
            StringBuffer tempText = new StringBuffer();
            for (XWPFParagraph xwpfParagraph : allParagraph) {
                List<XWPFRun> runList = xwpfParagraph.getRuns();
                for (XWPFRun xwpfRun : runList) {
                    tempText.append(xwpfRun.toString());
                }
            }
//            tempText.replace("${FIO}", student.getFIO());
            System.out.println(tempText.toString().replace("${FIO}", student.getFIO()));

//      2
//            XWPFParagraph para = (XWPFParagraph) xwpfParagraphElement;
//            for (XWPFRun run : para.getRuns()) {
//                if (run.getText(0) != null) {
//                    String text = run.getText(0);
//                    Matcher expressionMatcher = expression.matcher(text);
//                    if (expressionMatcher.find() && expressionMatcher.groupCount() > 0) {
//                        System.out.println("Expression Found...");
//                    }
//                }
//            }


        } catch (FileNotFoundException e) {
            // Файл не найден
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static String getCCV(Iterator<Cell> iterator) {
        Cell currentCell = iterator.next();
        System.out.println(formatter.formatCellValue(currentCell));
        return formatter.formatCellValue(currentCell);
    }
}
