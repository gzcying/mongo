package com.lida.mongo.controller;

import com.alibaba.fastjson.JSONObject;
import com.lida.mongo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017-2-10.
 */
@Controller
public class RestfulController {
    private Logger logger = LoggerFactory.getLogger(RestfulController.class);

    @RequestMapping(value = "/restful")
    public String restful( ){
        return "restfulTest";
    }

    @RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String hello() {
        return "你好！hello world bb";
    }

    @RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String say(@PathVariable(value = "msg") String msg) {
        return "{\"msg\":\"you say:'" + msg + "'\"}";
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person getPerson(@PathVariable("id") int id) {
        Person person = new Person("myname", id, null);
        return person;
    }

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "删除人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public @ResponseBody
    Object addPerson(Person person) {
        logger.info("注册人员信息成功id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "注册人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public @ResponseBody
    Object updatePerson(Person person) {
        logger.info("更新人员信息id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "更新人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PATCH)
    public @ResponseBody
    List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

        logger.info("查询人员name like " + name);
        List<Person> lstPersons = new ArrayList<Person>();

        Person person = new Person();
        person.setName("张三");
        lstPersons.add(person);

        Person person2 = new Person();
        person2.setName("李四");
        lstPersons.add(person2);

        Person person3 = new Person();
        person3.setName("王五");
        lstPersons.add(person3);

        return lstPersons;
    }
}
