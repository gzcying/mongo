package com.lida.mongo.templateDao;

import com.lida.mongo.entity.Person;

import java.util.List;

/**
 * Created by John on 2017-2-8.
 */
public interface PersonMongoDao {


     List<Person> findAll();
     void insertPerson(Person user);
     void removePerson(String userName);
     void updatePerson();
     List<Person> findForRequery(String userName);
}
