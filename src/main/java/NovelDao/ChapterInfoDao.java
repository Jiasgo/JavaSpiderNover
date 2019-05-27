package NovelDao;

import BeanNovel.Chapter_info;
import ConnMysql.ConnMysqlGetSession;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ChapterInfoDao {
    //插入每个章节的文字
    public static void insertChapterlInfo(Chapter_info chapter_info) {
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        sqlSession.insert("sql.insertChapterInfo", chapter_info);
        sqlSession.commit();
    }

    //查询每个章节的文字
    public static List<String> selectChaptersContent(int id){
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        List<String> result_content= sqlSession.selectList("sql.selectChaptersContent", id);
        return result_content;
    }

}
