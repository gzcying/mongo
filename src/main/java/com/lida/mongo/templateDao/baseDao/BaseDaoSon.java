package com.lida.mongo.templateDao.baseDao;

import com.lida.mongo.entity.Person;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

/**
 * Created by John on 2017-2-8.
 */
@Repository
public class BaseDaoSon extends BaseDaoImpl<Person,ObjectId> {
    public void save(Person person) {
        this.mongoTemplate.save(person, this.collectionName);
    }


}
