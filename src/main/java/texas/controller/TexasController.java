package texas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import texas.bean.UserInfo;
import texas.service.TexasService;

import javax.servlet.http.HttpServletRequest;

/**    ---Rayn add get   */

@Controller
@RequestMapping("texas")
public class TexasController {

    private static final String TAG = TexasController.class.getSimpleName();

    @Autowired
    TexasService texasService;

    @Autowired
    UserInfo userInfo;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public void login(final HttpServletRequest request) {
        System.out.println(TAG+"---Rayn login");
        userInfo.setWid(request.getParameter("wid"));
        userInfo.setWname(request.getParameter("wname"));
        texasService.save(userInfo);
    }

}
