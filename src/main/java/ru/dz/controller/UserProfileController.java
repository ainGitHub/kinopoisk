package ru.dz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.UserInfo;
import ru.dz.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Created by Admin on 29.11.2016.
 */

@Controller
public class UserProfileController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
    public String changeUserInformation(@PathVariable Long id) throws ParseException {
        String city = request.getParameter("city");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");

        String bd = "";
        if (birthday != null && !birthday.equals("")) {
            String numbers[] = new String[3];
            for (int i = 0; i < birthday.length(); i++) {
                numbers = birthday.split("-");
            }

            for (int i = 1; i <= numbers.length; i++) {
                bd += "." + numbers[numbers.length - i];
            }
            bd = bd.substring(1);
        }

        UserInfo user = userService.getUser(id);

        if (gender.equals("none")) {
            gender = "не указан";
        } else {
            if (gender.equals("man")) {
                gender = "мужской";
            } else {
                gender = "женский";
            }
        }

        if (user.getCity() == null || user.getCity().equals("") || !user.getCity().equals(city)) {
            user.setCity(city);
        }
        if (user.getGender() == null || user.getGender().equals("") || !user.getGender().equals(gender)) {
            user.setGender(gender);
        }
        if (user.getEmail() == null || user.getEmail().equals("") || !user.getEmail().equals(email)) {
            user.setEmail(email);
        }
//        if ((!bd.equals("")) && (user.getBirthday() == null || user.getBirthday().equals("") || !user.getBirthday().equals(bd))) {
//            user.setBirthday((bd));
//        }

        userService.addUser(user);
        request.getSession().setAttribute("user", user);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/delete-date/{id}", method = RequestMethod.GET)
    public String deleteDateFromProfileInfo(@PathVariable Long id) throws ParseException {
        UserInfo user = userService.getUser(id);
        //user.setBirthday("");
        userService.addUser(user);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("review", user.getReviews());
        return "redirect:/profile";
    }
}
