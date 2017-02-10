package com.lida.mongo.controller;

import com.lida.mongo.entity.Person;
import com.lida.mongo.service.TemplateService;
import org.slf4j.Logger;
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
@RequestMapping(value = "/test")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        logger.debug("test logger");
        List<Person> list = templateService.findAll();
        List<Person> list2 = templateService.findAll2();

        model.addAttribute("result", list);
        model.addAttribute("result2", list2);
        return "mogoList";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public String list2(Model model) {
        //templateService.insert();

        String id="589c0f2e2c0ae36ffc37e771";
        templateService.delete(id);

        String id2 = "589c0e892c0ae36ffc37e76f";
        templateService.update(id2);

        List<Person> list = templateService.findAll2();
        model.addAttribute("result", list);
        return "mogoList";
    }

}
