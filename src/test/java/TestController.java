import com.lida.mongo.templateDao.baseDao.BaseDaoSon;
import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by John on 2017-2-7.
 */
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 测试logger
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println("1111111111111111111");
        logger.debug("22222222222");

        String nes = "aaaaa";
        String olds = "bbbbb";
        System.out.printf("test printf : %s \n", nes);
        logger.debug("test logger new:{}. old: {}", nes, olds);
    }


    /**
     * 测试mongodb的数据库连接
     */
    @Test
    public void testMongodb() {
        try {
            // 连接到 mongodb 服务
            Mongo mongo = new Mongo("127.0.0.1", 27017);
            //根据mongodb数据库的名称获取mongodb对象 ,
            DB db = mongo.getDB("test");
            Set<String> collectionNames = db.getCollectionNames();
            // 打印出test中的集合
            for (String name : collectionNames) {
                System.out.println("collectionName===" + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnotation(){
        BaseDaoSon baseDaoSon=new BaseDaoSon();
    }
}
