package SpiderNovel;

import BeanNovel.Chapter_info;
import BeanNovel.Novel_info;
import NovelDao.NovelInfoDao;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static NovelDao.ChapterInfoDao.selectChaptersContent;

public class SpiderCommon {

    //获取请求头
    public static Map<String, String> getHeader(){
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "http://info.bet007.com");
        header.put("User-Agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0");
        header.put("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Language", "zh-cn,zh;q=0.5");
        header.put("Accept-Charset", "  GB2312,utf-8;q=0.7,*;q=0.7");
        header.put("Connection", "keep-alive");
        return header;
    }

    //得到documents对象
    public static Document getUrlDocument(String url) throws Exception{
        Connection connect = Jsoup.connect(url);
        Connection data = connect.headers(SpiderCommon.getHeader());
        //System.out.println(document);
        Document document = data.get();
        //System.out.println(document);
        return document;
    }


    //写入到本地txt文件
    public static boolean write2txt(Novel_info novel_info, Chapter_info chapter_info) throws Exception{
        boolean isWrite = false;
        String novelFileAddress = "novels_address/";
        String title = novel_info.getName();
        File dirs = new File(novelFileAddress + title);
        String dir = dirs.getAbsolutePath();
        if(!dirs.exists()) {
            dirs.mkdir();
        }
        File file = new File(dir + "/" + title + ".txt");
        System.out.println(file);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter writer = null;
        int id = NovelInfoDao.selectInfo_id(novel_info.getName());
        int t_size = 10;
        for(int i = 0; i < t_size; i++){
            List<String> text = selectChaptersContent(id);
            t_size = text.size();
            writer = new FileWriter(file, true);
            writer.append("【" + chapter_info.getTitle() + "】\n");
            writer.append("\t" + text.get(i));
            writer.append("\n\n");
            if(null!=writer){
                writer.close();
            }
        }
        isWrite = true;
        System.out.println("下载到本地完成！");
        return isWrite;
    }


}
