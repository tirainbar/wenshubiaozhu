import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class ExportExcel {
    public ExportExcel() {
    }
    /**
     *  导出Excel
     * @param sheetName 表格 sheet 的名称
     * @param dataList 需要显示的数据集合
     * @param exportExcelName 导出excel文件的名字
     * @param fatherPath 导出excel文件的根目录
     */
    public  static void getExcel(String sheetName, List<Fact> dataList,
                                 String exportExcelName,String fatherPath) {

        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);

        // 产生表格标题行
        String headers[]={"文书id","文书中顺序","事实内容"};

        XSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<Fact> it = dataList.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Fact data = it.next();

            XSSFCell cell1 = row.createCell(0);
            XSSFRichTextString id = new XSSFRichTextString(data.getInstrument_id()+"");
            cell1.setCellValue(id);
            XSSFCell cell2 = row.createCell(1);
            XSSFRichTextString num = new XSSFRichTextString(data.getNum()+"");
            cell2.setCellValue(num);
            XSSFCell cell3 = row.createCell(2);
            XSSFRichTextString text = new XSSFRichTextString(data.getText()+"");
            cell3.setCellValue(text);
        }
        OutputStream out = null;
        try {
            String tmpPath = fatherPath + exportExcelName + ".xlsx";
            out = new FileOutputStream(tmpPath);
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(workbook != null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
