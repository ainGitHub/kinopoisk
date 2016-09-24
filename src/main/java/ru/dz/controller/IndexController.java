package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aydar Farrakhov on 05.09.16.
 */
@Controller
public class IndexController {

    private static final String INDEX_TEMPLATE = "index";

    @RequestMapping("")
    private String get(){
        return INDEX_TEMPLATE;
    }

    @RequestMapping(value = "/index")
    private String index() {
        //// TODO: 24.09.2016 Разделить test.ftl на части
        return "test";
    }
}
