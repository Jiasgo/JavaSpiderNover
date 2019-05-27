package BeanNovel;

public class Novel_info {
    private int id;//小说的id
    private String name;//小说名字
    private String author;//作者
    private String type;//小说分类
    private int state;//小说状态
    private String lastestupdate;//最近更新时间
    private String lastestChapter;//最新章节
    private String intro;//简介

    //空参构造
    public Novel_info() {
    }

    //有参构造
    public Novel_info(int id, String name, String author, String type, int state, String lastestupdate, String lastestChapter, String intro) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.state = state;
        this.lastestupdate = lastestupdate;
        this.lastestChapter = lastestChapter;
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        /*if("1".equals(state)){
            return "完结";
        }else if("1".equals(state)){
            return "连载中";
        }else if("1".equals(state)){
            return "断更";
        }else{
            return "不知";
        }*/
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLastestupdate() {
        return lastestupdate;
    }

    public void setLastestupdate(String lastestupdate) {
        this.lastestupdate = lastestupdate;
    }

    public String getLastestChapter() {
        return lastestChapter;
    }

    public void setLastestChapter(String lastestChapter) {
        this.lastestChapter = lastestChapter;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        String novel_state;
        if (state == 1) {
            novel_state = "完结";
        } else if (state == 2) {
            novel_state = "连载中";
        } else if (state == 3) {
            novel_state = "断更";
        } else {
            novel_state ="不知";
        }
        return "小说名称：" + name + "\n作者：" + author + "\n类型：" + type + "\n状态：" + novel_state + "\n最新更新：" + "\n简介：" + intro;
    }
}
