package ru.dz.controller;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.AuthResponse;
import com.vk.api.sdk.objects.fave.responses.GetUsersResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.dz.entity.UserInfo;

import java.util.List;


/**
 * Created by ainur on 01.10.2016.
 */
@Controller
public class RegistrationController {
    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    private String VK_URL = "https://oauth.vk.com/authorize";
    private int VK_APP_ID = 5650812;
    private String VK_REDIRECT_URI = "http://localhost:8080/registration/vk/";
    private String VK_SECRET_KEY = "Z64QtM7PEAN24FDQ4l1m";


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

    @RequestMapping(value = "registration/vk/", method = RequestMethod.GET)
    @ResponseBody
    private List<UserXtrCounters> registerVK(@RequestParam String code) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);

        AuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(VK_APP_ID, VK_SECRET_KEY, VK_REDIRECT_URI, code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        List<UserXtrCounters> users = vk.users().get()
                .userIds(actor.getId() + "")
                .lang(Lang.RU)
                .execute();

        UserXtrCounters userXtrCounters = users.get(0);

        //// TODO: 11.10.2016 saving users
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userXtrCounters.getFirstName());
        userInfo.setSecondName(userXtrCounters.getLastName());

        logger.info(userXtrCounters.getBdate());
        logger.info(userXtrCounters.getFirstName());
        logger.info(userXtrCounters.getLastName());
        return users;
    }
}
