package NovelDao;

import BeanNovel.Novel_info;
import ConnMysql.ConnMysqlGetSession;
import org.apache.ibatis.session.SqlSession;

public class NovelInfoDao {

    public Novel_info selectNovelInfoById(String id) {
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        Novel_info novelInfo = sqlSession.selectOne("sql.selectNovelInfoById", id);
        return novelInfo;
    }


    //小说基本信息插入数据库
    public static void insertNovelInfo(Novel_info novelInfo) {
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        sqlSession.insert("sql.insertNovelInfo", novelInfo);
        sqlSession.commit();
    }

    //查询小说信息
    public static Novel_info selectInfo() {
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        Novel_info info = sqlSession.selectOne("sql.selectInfo");
        return info;
    }

    //获取小说新抓取的id
    public static int selectInfo_id(String name){
        SqlSession sqlSession = new ConnMysqlGetSession().getSqlSession();
        int id = sqlSession.selectOne("sql.selectInfo_id", name);
        return id;
    }

}
