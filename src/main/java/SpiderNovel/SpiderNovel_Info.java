package SpiderNovel;

import BeanNovel.Novel_info;
import NovelDao.NovelInfoDao;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static SpiderNovel.SpiderCommon.getUrlDocument;

public class SpiderNovel_Info {
    public static Novel_info re_N = new Novel_info();

    //获得网页头部小说信息集合
    public static Elements getHeadeMetas(String url) throws Exception{
        Document document = getUrlDocument(url);
        Elements metas = document.select("meta");
        return metas;
    }

    //获取小说信息
    public static Novel_info getNovelInfo(Novel_info novelInfo, String url) throws Exception{
        Elements metas = getHeadeMetas(url);
        for (Element meta : metas) {
            String content = meta.attr("content");
            if ("og:title".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("小说名："+content);
                novelInfo.setName(content);
            }
            if ("og:description".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("简介："+content);
                novelInfo.setIntro(content);
            }
            if ("og:novel:category".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("分类:"+content);
                novelInfo.setType(content);
            }
            if ("og:novel:author".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("作者:"+content);
                novelInfo.setAuthor(content);
            }
            if ("og:novel:status".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("状态:"+content);
                if("完结".equals(content)){
                    int state = 1;
                    novelInfo.setState(state);
                }else if("连载中".equals(content)){
                    int state = 2;
                    novelInfo.setState(state);
                }else if("断更".equals(content)){
                    int state = 3;
                    novelInfo.setState(state);
                }else{
                    int state = 4;
                    novelInfo.setState(state);
                }
            }
            if ("og:novel:update_time".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("更新时间:"+content);
                novelInfo.setLastestupdate(content);
            }
            if ("og:novel:latest_chapter_name".equalsIgnoreCase(meta.attr("property"))) {
                //System.out.println("最新章节:"+content);
                novelInfo.setLastestChapter(content);
            }
        }
        return novelInfo;
    }


    //将小说信息写进数据库
    public static Novel_info writeNovelInfo(String url) throws Exception {
        Novel_info novel_info = new Novel_info();
        novel_info = SpiderNovel_Info.getNovelInfo(novel_info, url);
        NovelInfoDao.insertNovelInfo(novel_info);//插入小说信息到数据库表
        System.out.println("写入小说信息到数据库成功！");
        return novel_info;
    }


}
