package com.lida.mongo.templateDao.baseDao;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by John on 2017-2-8.
 */
public abstract class BaseDaoImpl<T extends Serializable,PK extends Serializable> implements BaseDao<T,PK> {

    @Resource
    protected MongoTemplate mongoTemplate;

    protected final Class<T> entityClass;
    protected final String collectionName;

    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    /**
     * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型
     */
    public BaseDaoImpl() {
        //getClass() 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。

        this.entityClass = getSuperClassGenricType(this.getClass(),0);
        this.collectionName = getDocumentAntotationValue(entityClass);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * BaseDaoImpl<T>的构造函数中的 this 指的是子类的实例。
     * this.getClass()获取子类的真实类型;
     * c.getGenericSuperclass(); 获取泛型父类
     * t.getActualTypeArguments(); 通过该泛型父类来获取真实的类型参数的数组；也就是获取泛型父类的尖括号里面的参数<T>，
     * 因为泛型的尖括号是可以有多个参数的，所以该方法返回的是一个数组。
     * p[0]（既：t.getActualTypeArguments()[0]）； 获取该数组的第一个值。因为我们知道在该例子中BaseDaoImpl<T>只有一个参数T。所以我们只需要获取第一个值就可以了。
     */
    @SuppressWarnings("unchecked")
    private  Class getSuperClassGenricType(final Class clazz,final int index ) {
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index >= 0 && index < params.length) {
                if (params[index] instanceof Class)
                    return (Class) params[index];
            }
        }
        logger.error("获取泛型的真实类型失败！");
        return Object.class;
    }

    private String getDocumentAntotationValue(Class clazz) {
        //获取类上的注解值
        Document anno = (Document) (clazz.getAnnotation(Document.class));
        if (anno != null) {
            return anno.collection();
            //列出所有的方法并实现;
            //Method[] methods = anno.annotationType().getDeclaredMethods();
            /*for (Method me : methods) {
                if (!me.isAccessible()) {
                    me.setAccessible(true);
                }
                try {
                    if (me.getName().equals("collection"))
                        return (String) me.invoke(anno, null);
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }*/

        }
        return "";
    }

    @Override
    public DBCollection createCollection() {
        return mongoTemplate.createCollection(this.collectionName);
    }

    @Override
    public void insert(T object) {
        mongoTemplate.insert(object, this.collectionName);
    }

    @Override
    public WriteResult updateById_OneField(ObjectId objectId, String updateKey, Object updateValue) {
        return mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(objectId)), Update.update(updateKey, updateValue), this.entityClass, this.collectionName);
    }

    @Override
    public WriteResult removeById(ObjectId objectId) {
        return mongoTemplate.remove(Query.query(Criteria.where("_id").is(objectId)), this.entityClass, this.collectionName);
    }

    @Override
    public T findById(ObjectId objectId) {
        return mongoTemplate.findById(objectId,this.entityClass,this.collectionName);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(this.entityClass, this.collectionName);
    }
}
