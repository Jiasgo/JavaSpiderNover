package SpiderNovel;

import BeanNovel.Chapter_info;
import NovelDao.ChapterInfoDao;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static SpiderNovel.SpiderCommon.getUrlDocument;

public class SpiderChapter_Info {


    //获取每章节里的文字
    public static String getNovelChapterContent(String chapter_url) throws Exception{
        Document document = getUrlDocument(chapter_url);
        Elements content = document.select("#content");
        String text = content.text();
        return text;
    }

    //每个章节里的文字写进数据库
    public static Chapter_info writeChaptersInfo(String url, int id) throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Document document = getUrlDocument(url);
        Elements dd = document.select("dd");
        ArrayList<Chapter_info> chapter_info_list = new ArrayList();
        Chapter_info result_chapter_info = new Chapter_info();
        for (int i = 12; i< dd.size(); i++){
            Chapter_info chapter_info = new Chapter_info();
            long time = date.getTime();
            String writeTime = sdf.format(time);
            Element d = dd.get(i);
            Elements dd_a = d.select("a");
            chapter_info.setFictionId(id);//章节对应的小说id
            for (Element a_href : dd_a) {
                String link = a_href.attr("href");
                String chapter_url = url + link;
                chapter_info.setHtml(chapter_url);//章节html地址
                chapter_info.setContent(getNovelChapterContent(chapter_url));//章节内容
                chapter_info.setTitle(a_href.text());//章节标题
                chapter_info.setCreateDate(writeTime);//章节存储时间
            }

            chapter_info_list.add(chapter_info);
            ChapterInfoDao.insertChapterlInfo(chapter_info);//插入数据库表
            result_chapter_info = chapter_info;

        }
        System.out.println("爬取存库完成！");
        return result_chapter_info;
    }

}
