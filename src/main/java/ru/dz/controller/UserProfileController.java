package ru.dz.controller;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dz.entity.Review;
import ru.dz.entity.UserInfo;
import ru.dz.services.FilmService;
import ru.dz.services.ReviewService;
import ru.dz.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 29.11.2016.
 */

@Controller
public class UserProfileController {
    private static final String PROFILE_MAPPING = "/profile";
    private static final String CHANGE_MAPPING = "/change";
    private static final String DELETE_DATE_MAPPING = "/delete-date";
    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    FilmService filmService;

    @Autowired
    ReviewService reviewService;

    Logger logger = LoggerFactory.logger(UserProfileController.class);

    @RequestMapping(value = PROFILE_MAPPING, method = RequestMethod.GET)
    public String renderProfilePage(ModelMap model) {
        Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = null;

        if (userObj != null && !(userObj instanceof String)) {
            user = (User) userObj;
        }

        UserInfo userInfo = userService.findUserByVkID(Integer.parseInt(user.getUsername()));
        logger.info(userInfo.getUsername());

        //// TODO: 03.12.2016 Постараться каким то образом вывести это в сервис
        ArrayList<Review> reviews = (ArrayList<Review>) reviewService.getAllReviewsByUser(userInfo);
        model.put("films", filmService.findAll());
        model.put("review", reviews);
        model.put("user", userInfo);
        return "profile";
    }

    @RequestMapping(value = CHANGE_MAPPING + "/{id}", method = RequestMethod.GET)
    public String changeUserInformation(@PathVariable Long id) throws ParseException {
        String city = request.getParameter("city");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String day = request.getParameter("birthday");
        Date birthday = new Date();
        if (!day.equals("")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthday = dateFormat.parse(day);
        }

        UserInfo user = userService.getUser(id);

        switch (gender) {
            case "none":
                gender = "не указан";
                break;
            case "man":
                gender = "мужской";
                break;
            case "woman":
                gender = "женский";
                break;
        }


        if (!city.equals("")) {
            if ((user.getCity() == null || user.getCity().equals("") || !user.getCity().equals(city)) || city.equals("")) {
                user.setCity(city);
            }
        } else {
            user.setCity(null);
        }

        if (!email.equals("")) {
            if (user.getEmail() == null || user.getEmail().equals("") || !user.getEmail().equals(email)) {
                user.setEmail(email);
            }
        } else {
            user.setEmail(null);
        }
        if (user.getGender() == null || !user.getGender().equals(gender)) {
            user.setGender(gender);
        }
        if (!day.equals("")) {
            if ((user.getBirthday() == null || !user.getBirthday().equals(birthday))) {
                user.setBirthday(birthday);
            }
        }

        userService.addUser(user);
        request.getSession().setAttribute("user", user);
        return "redirect:/profile";
    }

    @RequestMapping(value = DELETE_DATE_MAPPING + "/{id}", method = RequestMethod.GET)
    public String deleteDateFromProfileInfo(@PathVariable Long id) throws ParseException {
        UserInfo user = userService.getUser(id);
        user.setBirthday(null);
        userService.addUser(user);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("review", user.getReviews());
        return "redirect:/profile";
    }
}
