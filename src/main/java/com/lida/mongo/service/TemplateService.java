package com.lida.mongo.service;

import com.lida.mongo.entity.Address;
import com.lida.mongo.entity.Person;
import com.lida.mongo.templateDao.PersonMongoDao;
import com.lida.mongo.templateDao.baseDao.BaseDaoSon;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017-2-9.
 */
@Service
public class TemplateService {
    @Autowired
    private PersonMongoDao personMongoDao;
    @Autowired
    private BaseDaoSon baseDaoSon;

    public void insert() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            persons.add(new Person("name" + i, i, new Address("石家庄aa", "裕华路", i)));
        }
        Person person = new Person("test", 0000, new Address("广州aa", "路路", 0));
        baseDaoSon.insert(person);

        person.setAge(9999);
        baseDaoSon.save(person);

    }

    public void update(String id){
        baseDaoSon.updateById_OneField(new ObjectId(id),"name","gggg");
    }

    public void delete(String id) {
        ObjectId objectId=new ObjectId(id);
        baseDaoSon.removeById(objectId);
    }


    /**
     * 使用泛型dao
     */
    public List<Person> findAll() {
        return baseDaoSon.findAll();
    }

    /**
     * 没有使用泛型dao
     */
    public List<Person> findAll2() {
        return personMongoDao.findAll();
    }

    public List<Person> findByAge(String userName) {
        return personMongoDao.findForRequery(userName);
    }
}

