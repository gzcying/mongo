package com.lida.mongo.templateDao.baseDao;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John on 2017-2-8.
 */
public interface BaseDao<T,PK extends Serializable> {
    //创建集合
    public DBCollection createCollection();

    //添加
    public void insert(T object);

    //修改
    public WriteResult updateById_OneField(ObjectId objectId,String updateKey,Object updateValue);

    //根据条件删除
    public WriteResult removeById(ObjectId objectId);

    //根据条件查找
    public T findById(ObjectId objectId);

    //查找所有
    public List<T> findAll();

}
