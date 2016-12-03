package ru.dz.controller;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.AuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.dz.entity.UserInfo;
import ru.dz.services.UserService;

import java.util.List;


/**
 * Created by ainur on 01.10.2016.
 */
@Controller
public class RegistrationController {
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    //// TODO: 13.10.2016 move constants to config file
    private String VK_URL = "https://oauth.vk.com/authorize";
    private int VK_APP_ID = 5650812;
    private String VK_REDIRECT_URI = "http://localhost:8080/registration/vk";
    private String VK_SECRET_KEY = "Z64QtM7PEAN24FDQ4l1m";

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private String registerPage() {
        return "register";
    }

    @RequestMapping(value = "/registration_vk", method = RequestMethod.GET)
    private ModelAndView registerLinkVK() {

        return new ModelAndView(new RedirectView(VK_URL + "?client_id=" + VK_APP_ID +
                "&redirect_uri=" + VK_REDIRECT_URI +
                "&scope=email,user_location,offline&state=registration", true, true, true));
    }


    @RequestMapping(value = "/registration/vk", method = RequestMethod.GET)
    private String registerVK(@RequestParam String code) throws ClientException, ApiException {
        //// TODO: 19.10.2016 Refactoring. Move the code to services
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);

        AuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(VK_APP_ID, VK_SECRET_KEY, VK_REDIRECT_URI, code)
                .execute();

        List<UserXtrCounters> users = vk.users().get()
                .userIds(authResponse.getUserId() + "")
                .fields(UserField.BDATE, UserField.COUNTRY, UserField.CITY, UserField.PHOTO_200)
                .lang(Lang.RU)
                .execute();

        UserXtrCounters userXtrCounters = users.get(0);

        UserInfo checkUser = userService.findUserByVkID(userXtrCounters.getId());

        if (checkUser == null) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(userXtrCounters.getFirstName());
            userInfo.setSecondName(userXtrCounters.getLastName());
            if (userXtrCounters.getCity() != null)
                userInfo.setCity(userXtrCounters.getCity().getTitle());
            userInfo.setVkId(userXtrCounters.getId());
            userInfo.setImage(userXtrCounters.getPhoto200());
            userService.addUser(userInfo);

            checkUser = userInfo;
        } else {
            logger.info("User already exist " + checkUser);
        }

        UserDetails user = new User(checkUser.getUsername(), checkUser.getVkId() + "", checkUser.getUserRoles());
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        request.getSession().setAttribute("user", checkUser);
        return "redirect:/profile";
    }
}
