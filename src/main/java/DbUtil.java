import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUtil {
    public DbUtil() {
    }
    //文书表部分
    /**
     *  根据instrument_id是否相等判断文书表instrument中是否已存在该条目
     * @param sql sql语句
     * @param instrument 文书类
     * @return List 数据集合
     */
    public static List selectOneInstrument(String sql,Instrument instrument) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    if ( rs.getString("instrument_id").equals(instrument.getInstrument_id())) {
                        list.add(Integer.valueOf(1));
                    } else {
                        list.add(Integer.valueOf(0));
                    }
                }
                return list;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    /**
     * 向文书表instrument中写数据，???顺序为instrument_id，num,xml
     * @param sql sql语句
     * @param instrument 文书类
     */
    public static void insertInstrument(String sql, Instrument instrument) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setString(1, instrument.getInstrument_id());
            ps.setInt(2, instrument.getNum());
            ps.setString(3, instrument.getXml());
            boolean flag = ps.execute();
            if (!flag) {
                System.out.println("Save 表Instrument : Instrument_id = " +  instrument.getInstrument_id() +  ",num = "+ instrument.getNum()+",xml = "+ instrument.getXml() +" succeed!");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //statute表部分
    /**
     *  根据法条name获取法条id
     * @param sql sql语句
     * @param statute 法条类
     * @return Statute 法条类
     */
    public static Statute  selectStatute_idWhereName(String sql,Statute statute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    {
                        statute.setStatute_id(rs.getString("statute_id"));
                    }
                }
                return  statute;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return  statute;
    }
    /**
     *  根据statute_id判断Statute表中是否已存在该条目
     * @param sql sql语句
     * @param statute 法条类
     * @return List 数据集合
     */
    public static List selectOneStatute(String sql, Statute statute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    if ( rs.getString("statute_id").equals(statute.getStatute_id())  ) {
                        list.add(Integer.valueOf(1));
                    } else {
                        list.add(Integer.valueOf(0));
                    }
                }
                return list;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    /**
     * 向statute表中写数据，???顺序为statute_id，name,text
     * @param sql sql语句
     * @param statute 法条类
     */
    public static void insertStatute(String sql, Statute statute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setString(1, statute.getStatute_id());
            ps.setString(2, statute.getName());
            ps.setString(3, statute.getText());
            boolean flag = ps.execute();
            if (!flag) {
                System.out.println("Save 表Statute : Statute_id = " +  statute.getStatute_id() +  " succeed!");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //Instrument-Statute表部分
    /**
     *  根据instrument_id和statute_id判断Instrument-Statute表中是否已存在该条目
     * @param sql sql语句
     * @param instrumentAndStatute 文书-法条类
     * @return List 数据集合
     */
    public static List selectOneInstrumentAndStatute(String sql, InstrumentAndStatute instrumentAndStatute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    if ( rs.getString("instrument_id").equals(instrumentAndStatute.getInstrument_id()) && rs.getString("statute_id").equals(instrumentAndStatute.getStatute_id())  ) {
                        list.add(Integer.valueOf(1));
                    } else {
                        list.add(Integer.valueOf(0));
                    }
                }
                return list;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    /**
     *  根据法条name获取法条id
     * @param sql sql语句
     * @param instrumentAndStatute 文书-法条类
     * @return InstrumentAndStatute 文书-法条类
     */
    public static InstrumentAndStatute  selectStatute_idWhereStatute_Name(String sql,InstrumentAndStatute instrumentAndStatute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    {
                        instrumentAndStatute.setStatute_id(rs.getString("statute_id"));
                    }
                }
                return  instrumentAndStatute;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return  instrumentAndStatute;
    }
    /**
     * 往Instrument-Statute表中写数据，???顺序为instrument_id,statute_id,num
     * @param sql sql语句
     * @param instrumentAndStatute 文书-法条类
     */
    public static void insertInstrumentAndStatute(String sql, InstrumentAndStatute instrumentAndStatute) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setString(1,instrumentAndStatute.getInstrument_id());
            ps.setString(2,instrumentAndStatute.getStatute_id());
            ps.setInt(3,instrumentAndStatute.getStatute_num());
            boolean flag = ps.execute();
            if (!flag) {
                System.out.println("Save 表InstrumentandStatute : Instrument_id = " +  instrumentAndStatute.getInstrument_id() +  ",Statute_id = "+ instrumentAndStatute.getStatute_id()+" succeed!");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //Fact表部分
    /**
     *  根据instrument_id，num判断Fact表中是否已存在该条目
     * @param sql sql语句
     * @param fact 事实类
     * @return List 数据集合
     */
    public static List selectOneFact(String sql, Fact fact) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    if ( rs.getString("instrument_id").equals(fact.getInstrument_id())  && rs.getInt("num")== fact.getNum() ) {
                        list.add(Integer.valueOf(1));
                    } else {
                        list.add(Integer.valueOf(0));
                    }
                }
                return list;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    /**
     * 往Fact表中写数据，???顺序为instrument_id,text,num
     * @param sql sql语句
     * @param fact 事实类
     */
    public static void insertFact(String sql, Fact fact) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            ps.setString(1, fact.getInstrument_id());
            ps.setString(2, fact.getText());
            ps.setInt(3, fact.getNum());
            boolean flag = ps.execute();
            if (!flag) {
                System.out.println("Save 表fact : Instrument_id = " +  fact.getInstrument_id() +  ",num = "+ fact.getNum()+" succeed!");
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    //导出excel部分
    /**
     *  根据Statute表name获取相关联的事实
     * @param sql sql语句
     * @param lawName 法条name
     * @return List<Fact> 数据集合
     */
    public static List<Fact>  selectFactWhereStatuteName(String sql,String lawName) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        new Fact();
        List<Fact> list = new ArrayList();
        try {
            Class.forName(Main.SQL_NAME);
            conn = DriverManager.getConnection(Main.SQL_URL, Main.SQL_USER, Main.SQL_PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(true) {
                while(rs.next()) {
                    {
                        Fact fact=new Fact();
                        fact.setFact_id(rs.getInt("fact_id"));
                        fact.setNum(rs.getInt("num"));
                        fact.setInstrument_id(rs.getString("instrument_id"));
                        fact.setText(rs.getString("text"));
                        list.add(fact);
                    }
                }
                return  list;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return  list;
    }
}
