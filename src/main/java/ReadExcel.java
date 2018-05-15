import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    public ReadExcel() {
    }
    /**
     *  从excelpath读取文书信息
     * @param excelpath 文书路径
     * @return  List<Instrument> 文书的数据集合
     */
    public List<Instrument> readInstrument(String excelpath) throws IOException {
        List<Instrument> list = new ArrayList();
        Instrument instrument = new Instrument();
        //根据/截取文件名
        String excelFileName = excelpath.substring(excelpath.lastIndexOf("\\")+1,excelpath.length()) ;
        //根据文件名截取文书序号，并转换为int
        int instrument_num =Integer.parseInt(getInstrumentNum(excelFileName));
        //根据文件名截取文书xml
        String xml = getInstrumentXml(excelFileName);
        instrument.setXml(xml);//获得文书名
        instrument.setInstrument_id(excelFileName+"_xsys");//获得文书id
        instrument.setNum(instrument_num);//获得文书序号
        list.add(instrument);
        return list;
    }
    //根据_截取文件序号
    public String getInstrumentNum(String excelFileName){

        if(excelFileName.indexOf("_")!= -1) {
            excelFileName = excelFileName.substring(0, excelFileName.indexOf("_"));
        }
        return excelFileName;
    }
    //根据第一个_和最后一个_截取xml
    public String getInstrumentXml(String excelFileName){

        if(excelFileName.indexOf("_")!= -1 && excelFileName.lastIndexOf("_")!= -1) {
            excelFileName = excelFileName.substring(excelFileName.indexOf("_")+1,excelFileName.lastIndexOf("_"));
        }
        return excelFileName;
    }

    //statute表部分
    //根据：截取法条名称
    public String getStatute_name(String statute_name){

        if(statute_name.indexOf(":")!= -1) {
            statute_name = statute_name.substring(0, statute_name.indexOf(":"));
        }else if(statute_name.indexOf("：")!= -1)
        {
            statute_name = statute_name.substring(0, statute_name.indexOf("："));
        }
        return statute_name;
    }
    /**
     *  从excelpath读取法条信息
     * @param excelpath 文书路径
     * @return  List<Statute> 法条的数据集合
     */
    public List<Statute> readStatute(String excelpath) throws IOException {
        InputStream is = new FileInputStream(excelpath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Statute> list = new ArrayList();
        for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); ++numSheet) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet != null) {
                HSSFRow hssfRow = hssfSheet.getRow(0);
                if (hssfRow != null) {
                    for(int colNum = 1; colNum < hssfRow.getLastCellNum()+1; colNum++) {
                        if(hssfRow.getCell(colNum) != null) {
                            Statute statute = new Statute();
                            HSSFCell text = hssfRow.getCell(colNum);
                            String name = getStatute_name(text.getStringCellValue());
                            statute.setName(name);//获得法条名称
                            statute.setText(text.getStringCellValue());//获得法条内容
                            //法条id还未获取，后面根据法条name从其他地方读取
                            list.add(statute);
                        }
                    }
                }
            }
        }
        return list;
    }

      //Instrumen_statute表部分
    /**
     *  从excelpath读取文书-法条信息
     * @param excelpath 文书路径
     * @return  List<InstrumentAndStatute> 文书-法条的数据集合
     */
     public List<InstrumentAndStatute> readInstrumentAndStatute(String excelpath) throws IOException {
        String excelFileName = excelpath.substring(excelpath.lastIndexOf("\\")+1,excelpath.length()) ;//根据/截取文件名
        String instrument_id=excelFileName+"_xsys";
        InputStream is = new FileInputStream(excelpath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<InstrumentAndStatute> list = new ArrayList();
        for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); ++numSheet) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet != null) {
                HSSFRow hssfRow = hssfSheet.getRow(0);
                if (hssfRow != null) {
                    for(int colNum = 1; colNum < hssfRow.getLastCellNum()+1; colNum++) {
                        if(hssfRow.getCell(colNum) != null) {
                            InstrumentAndStatute instrumentAndStatute = new InstrumentAndStatute();
                            HSSFCell text = hssfRow.getCell(colNum);
                            String name = getStatute_name(text.getStringCellValue());
                            instrumentAndStatute.setStatute_id(name);//获得法条名称，后面通过该数据从数据库读取id
                            instrumentAndStatute.setInstrument_id(instrument_id);//获得文书id
                            instrumentAndStatute.setStatute_num(colNum);//获得法条在文书中顺序
                            list.add(instrumentAndStatute);
                        }
                    }
                }
            }
        }
        return list;
    }

    //fact表部分
    /**
     *  从excelpath读取事实信息
     * @param excelpath 文书路径
     * @return List<Fact> 事实的数据集合
     */
    public List<Fact> readFact(String excelpath) throws IOException {
        InputStream is = new FileInputStream(excelpath);
        //根据/截取文件名
        String excelFileName = excelpath.substring(excelpath.lastIndexOf("\\")+1,excelpath.length()) ;
        String instrument_id=excelFileName+"_xsys";
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Fact> list = new ArrayList();
        for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); ++numSheet) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet != null) {
                for(int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); ++rowNum) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        Fact fact = new Fact();
                        if(hssfRow.getCell(0)!= null) {
                            HSSFCell text = hssfRow.getCell(0);
                            fact.setText(text.getStringCellValue());
                            fact.setNum(rowNum);
                            fact.setInstrument_id(instrument_id);
                            list.add(fact);
                        }
                    }
                }
            }
        }
        return list;
    }

}
