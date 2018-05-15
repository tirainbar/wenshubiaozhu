public class InstrumentAndStatute {

//    文书-法条（文书引用哪些法条）表 InstrumentandStatute
//    instrument_id	Varchar	文书id，
//    statute_id	varchar	法条Id，
//    num	int	文书中顺序
    private String instrument_id;	//文书id，例：005_1305111.xml_ft2jl_xxys(在后面加_xsys，代表刑事一审)
    private String statute_id;//法条Id，例： Lxxx.aaa-bb.cc.dd.ee
    private Integer statute_num;//法条在文书中顺序


    public InstrumentAndStatute() {
    }
    public String getInstrument_id() {
        return this.instrument_id;
    }
    public void setInstrument_id(String id) {
        this.instrument_id = id;
    }
    public String getStatute_id() {
        return this.statute_id;
    }
    public void setStatute_id(String id) {
        this.statute_id = id;
    }
    public Integer getStatute_num() {
        return statute_num;
    }
    public void setStatute_num(Integer statute_num) {
        this.statute_num = statute_num;
    }

}
