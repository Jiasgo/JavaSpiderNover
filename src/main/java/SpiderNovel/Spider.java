package SpiderNovel;


import BeanNovel.Chapter_info;
import BeanNovel.Novel_info;
import NovelDao.NovelInfoDao;


import static NovelDao.NovelInfoDao.selectInfo;
import static SpiderNovel.SpiderChapter_Info.writeChaptersInfo;
import static SpiderNovel.SpiderCommon.write2txt;
import static SpiderNovel.SpiderNovel_Info.writeNovelInfo;

public class Spider {
    public static void main(String[] args) throws Exception{
        //String url = "http://www.shuquge.com/txt/8072/";//飞剑问道
        //String url = "http://www.shuquge.com/txt/63542/";//三寸人间
        String url = "http://www.shuquge.com/txt/8400/";//

        Novel_info novel_info = null;
        Chapter_info chapter_info = null;

        novel_info = writeNovelInfo(url);//小说信息
        int id = NovelInfoDao.selectInfo_id(novel_info.getName());
        chapter_info = writeChaptersInfo(url, id);//章节内容

        write2txt(novel_info, chapter_info);//写到本地

        Novel_info info = selectInfo();
        System.out.println("info:" + info.toString());
    }
}
