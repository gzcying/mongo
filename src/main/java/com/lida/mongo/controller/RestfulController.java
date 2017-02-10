package com.lida.mongo.controller;

import com.lida.mongo.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by John on 2017-2-10.
 */
@Controller
public class RestfulController {
    private Logger logger = LoggerFactory.getLogger(RestfulController.class);

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

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public Person getPerson(@PathVariable("id") int id) {
        Person person = new Person("myname", id, null);
        return person;
    }

    @RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
    public @ResponseBody
    Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);

        //JSONPObject jsonObject = new JSONPObject();
        //jsonObject.("msg", "删除人员信息成功");
        //return jsonObject;
        return null;
    }
}
