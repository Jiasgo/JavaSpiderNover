package BeanNovel;

public class Chapter_info {
    private int id;//章节id
    private int fictionId;//此章节对应的小说
    private String title;//章节标题
    private String content;//章节内容
    private String html;//此章节的整个html页面
    private String createDate;//记录生成时间

    public Chapter_info() {
    }

    public Chapter_info(int id, int fictionId, String title, String content, String html, String createDate) {
        this.id = id;
        this.fictionId = fictionId;
        this.title = title;
        this.content = content;
        this.html = html;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFictionId() {
        return fictionId;
    }

    public void setFictionId(int fictionId) {
        this.fictionId = fictionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Chapter_info{" +
                "id=" + id +
                ", fictionId=" + fictionId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", html='" + html + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
