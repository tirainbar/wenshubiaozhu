public class Statute {
//    法条表 Statute
//    Statute_id	Varchar	Id,使用规则编码
//    Lxxx.aaa-bb.cc.dd.ee
//    法合，法条序号，之序，款序，项，目
//    name	Varchar	法条名称
//    text	Text	法条内容
    private String statute_id;//法条Id，例： Lxxx.aaa-bb.cc.dd.ee
    private String name;//法条名称
    private String text;//法条内容
    public Statute(){}
    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getStatute_id() {
        return statute_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatute_id(String statute_id) {
        this.statute_id = statute_id;
    }
}
