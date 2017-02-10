package com.lida.mongo.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.lida.mongo.entity.Person;
import com.lida.mongo.service.JpaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by John on 2017-2-8.
 */
@Controller
@RequestMapping(value = "/")
public class JpaController {
    @Autowired
    private JpaService jpaService;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(JpaController.class);

    static {
        System.out.println("test 禁止mongo 驱动的logger");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public String go(Model model) {
        jpaService.insert();
        List<Person> all = jpaService.findAll();
        model.addAttribute("result", all);
        return "go";
    }

    @RequestMapping(value = "/go2", method = RequestMethod.GET)
    public String go2(Model model) {
        jpaService.delete();
        List<Person> all = jpaService.findAll();
        model.addAttribute("result", all);
        return "go";
    }

    @RequestMapping(value = "/go3", method = RequestMethod.GET)
    public String go3(Model model) {
        List<Person> byAge = jpaService.findByAge(1, 889998, "test");
        model.addAttribute("result", byAge);

        List<Person> list = jpaService.findByName("test");
        model.addAttribute("result2", list);


        return "go";
    }


}
