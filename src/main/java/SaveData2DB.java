import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveData2DB {
    public SaveData2DB() {
    }
    /**
     *  向文书Instrument表中存数据
     * @param excelpath 文书路径
     */
    public void saveInstrument(String excelpath) throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        Instrument instrument = null;
        List<Instrument> list = new ArrayList<Instrument>();
        list = xlsMain.readInstrument(excelpath);
        for(int i = 0; i < list.size(); ++i) {
            instrument = (Instrument)list.get(i);
            List l = DbUtil.selectOneInstrument("select instrument_id from instrument where instrument_id = '" + instrument.getInstrument_id() + "'", instrument);
           if (!l.contains(Integer.valueOf(1)))
                {
                DbUtil.insertInstrument("insert into instrument(instrument_id,num,xml) values(?,?,?)", instrument);
            } else {
                System.out.println("instrument表中该记录已存在 :  instrument_id = " + instrument.getInstrument_id() + " and has been throw away!");
            }
        }
    }

    /**
     *  向法条statute表中存数据
     * @param excelpath 文书路径
     */
    public void saveStatute(String excelpath) throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        Statute statute = null;
        List<Statute> list = new ArrayList();
        list = xlsMain.readStatute(excelpath);
        for(int i = 0; i < list.size(); ++i) {
            statute = (Statute)list.get(i);
            String name = statute.getName();
            String doc_name = name.substring(0,name.indexOf("("));
            String article_seq = name.substring(name.indexOf(")")+1,name.length());
            //statute表写数据部分
            DbUtil.selectStatute_idWhereName("select code as statute_id from law_1_article where ARTICLE_SEQ = '"+ article_seq +"' and DOC_NAME = '"+ doc_name +"'",statute);
            List l = DbUtil.selectOneStatute("select * from statute where statute_id = '" + statute.getStatute_id() + "'", statute);
            if (!l.contains(Integer.valueOf(1))) {
                DbUtil.insertStatute("insert into statute(statute_id,name,text) values(?,?,?)",statute);
            } else {
                System.out.println("statute表中该记录已存在 :  statute_id = " + statute.getStatute_id() + " and has been throw away!");
            }
        }
    }

    /**
     *  向文书-法条InstrumentAndStatute表中存数据
     * @param excelpath 文书路径
     */
    public void saveInstrumentAndStatute(String excelpath) throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        InstrumentAndStatute instrumentAndStatute = null;
        List<InstrumentAndStatute> list = new ArrayList();
        list = xlsMain.readInstrumentAndStatute(excelpath);
        for(int i = 0; i < list.size(); ++i) {
            instrumentAndStatute = (InstrumentAndStatute)list.get(i);
            //InstrumentAndStatute表存数据部分
            DbUtil.selectStatute_idWhereStatute_Name("select statute_id from statute where name = '"+ instrumentAndStatute.getStatute_id() +"'",instrumentAndStatute);
            List l = DbUtil.selectOneInstrumentAndStatute("select * from instrumentandstatute where instrument_id ='"+ instrumentAndStatute.getInstrument_id() +"'and statute_id = '" + instrumentAndStatute.getStatute_id() + "'", instrumentAndStatute);
            if (!l.contains(Integer.valueOf(1))) {
                DbUtil.insertInstrumentAndStatute("insert into instrumentandstatute(instrument_id,statute_id,num) values(?,?,?)",instrumentAndStatute);
            } else {
                System.out.println("instrumentandstatute表中该记录已存在 : instrument_id = " + instrumentAndStatute.getInstrument_id() + ", statute_id = " + instrumentAndStatute.getStatute_id() + " and has been throw away!");
            }
        }
    }

    /**
     *  向事实fact表中存数据
     * @param excelpath 文书路径
     */
    public void saveFact(String excelpath) throws IOException, SQLException {
        ReadExcel xlsMain = new ReadExcel();
        Fact fact = null;
        List<Fact> list = new ArrayList();
        list = xlsMain.readFact(excelpath);
        for(int i = 0; i < list.size(); ++i) {
            fact = (Fact)list.get(i);
            List l = DbUtil.selectOneFact("select * from fact where instrument_id = '" + fact.getInstrument_id() + "'and num = "+ fact.getNum()+"", fact);
            if (!l.contains(Integer.valueOf(1))) {
                DbUtil.insertFact("insert into fact(instrument_id,text,num) values(?,?,?)",fact);
            }else {
                System.out.println("fact表中该记录已存在 :  instrument_id = " + fact.getInstrument_id() + ",num = " + fact.getNum() + " and has been throw away!");
            }
        }
    }
}
