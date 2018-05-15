public class Instrument {
    //    文书表 instrument
    //    Instrument_id	Varchar	Id，例：005_1305111.xml_ft2jl_xxys(在后面加_xxys，代表刑事一审)
    //    Num	Int	序号，例： 5
    //    Xml	Varchar	文书名，例：1305111.xml

    private String instrument_id;	//文书id，例：005_1305111.xml_ft2jl_xxys(在后面加_xsys，代表刑事一审)
    private String xml;//文书名，例：1305111.xml
    private Integer num;//文书序号（005）
    public Instrument() {
    }
    public String getInstrument_id() {
        return this.instrument_id;
    }
    public void setInstrument_id(String id) {
        this.instrument_id = id;
    }
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
}
