package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adel on 05.11.16.
 */
@Controller
public class MainPageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderMainPage() {
        return "mainPage";
    }
}
