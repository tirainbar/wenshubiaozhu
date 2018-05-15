import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }
    public static final String SQL_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String SQL_URL = "jdbc:mysql://localhost:3306/wenshubiaozhu_new?serverTimezone=GMT%2B8&characterEncoding=utf8&useSSL=false";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "root";

    public static void readExcel(){
        SaveData2DB saveData2DB = new SaveData2DB();
        String menuPath = "500篇_事实与法条/";//文书路径的父文件夹路径
        try {
            ArrayList<File> fileList = FileGet.getFiles(menuPath);
            for(int i=0;i<fileList.size();i++) {
                String excelpath = fileList.get(i).getPath();//循环获取文书路径
                saveData2DB.saveInstrument(excelpath);//存文书表
                saveData2DB.saveStatute(excelpath);//存法条表
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
        try {
            ArrayList<File> fileList = FileGet.getFiles(menuPath);
            for(int i=0;i<fileList.size();i++) {
                String excelpath = fileList.get(i).getPath();//循环获取文件路径
                saveData2DB.saveInstrumentAndStatute(excelpath);//存文书-法条表
                saveData2DB.saveFact(excelpath);//存事实表
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }
    public static void exportExcel(){
        List<Fact> list = new ArrayList();//需要导出的数据集
        String lawName = "中华人民共和国刑法(2015)第一百三十三条";//根据法条名称导出相关联事实
        String exportExcelName = lawName + "_相关联事实";//导出的文档命名
        String fatherPath = "D:/javaworkspace/wenshubiaozhu/";//导出的目录
        String sql="select fact.fact_id,fact.instrument_id,fact.text,fact.num from  fact,statute,judgement where  fact.fact_id=judgement.fact_id  and statute.statute_id = judgement.statute_id and judgement.isrelated = 1 and statute.name =  '" + lawName + "'";
        // String sql="select fact_id,num,instrument_id,text from fact where fact_id >1000 and fact_id<1500";//测试语句
        try {
            list = DbUtil.selectFactWhereStatuteName(sql,lawName);
            ExportExcel.getExcel(lawName,list,exportExcelName,fatherPath);
        }catch (Exception var10) {
            var10.printStackTrace();
        }
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        //readExcel();//执行导入excel操作
        //exportExcel();//执行导出excel操作
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        System.out.println("end");
    }
}
