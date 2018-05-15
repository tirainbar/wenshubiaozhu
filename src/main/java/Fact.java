public class Fact {

//    事实表 fact
//    字段名	数据类型	说明
//    fact_id	Int,自增	Id
//    instrument_id	Varchar	文书id
//    text	Text	事实内容
//    num	Int	文书中顺序

    private Integer fact_id;
    private Integer num;
    private String text;
    private String instrument_id;
    public Fact() {
    }
    public Integer getFact_id() {
        return this.fact_id;
    }
    public void setFact_id(Integer id) {
        this.fact_id = id;
    }
    public Integer getNum() {
        return this.num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getInstrument_id() {
        return this.instrument_id;
    }
    public void setInstrument_id(String id) {
        this.instrument_id = id;
    }
}
