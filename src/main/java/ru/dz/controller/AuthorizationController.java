package ru.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ainur on 12.10.2016.
 */
@Controller
public class AuthorizationController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String loginPage() {
        return "login";
    }

    //// TODO: 13.10.2016 add authorization methods
}
