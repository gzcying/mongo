package com.lida.mongo.service;

import com.lida.mongo.jpaDao.PersonDao;
import com.lida.mongo.entity.Address;
import com.lida.mongo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017-2-9.
 */
@Service
public class JpaService {
    @Autowired
    private PersonDao personDao;

    public void insert() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            persons.add(new Person("name" + i, i, new Address("石家庄", "裕华路", i)));
        }
        Person person = new Person("test", 777, new Address("广州", "路路", 0));
        personDao.insert(person);
        person.setAge(888);
        personDao.save(person);
        personDao.save(persons);
    }

    public void delete() {
        List<Person> all = personDao.findAll();
        if (!all.isEmpty())
            personDao.delete(all.get(0));
    }

    public List<Person> findByAge(int ageGt, int ageLt, String name) {
        return personDao.findByAge(ageGt, ageLt, name);
    }

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public  List<Person> findByName(String name){
        return personDao.findByNameLike(name);
    }
}
