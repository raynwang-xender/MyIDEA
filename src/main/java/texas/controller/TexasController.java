package texas.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import texas.bean.Conf;
import texas.bean.UserInfo;
import texas.service.TexasService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**    ---Rayn add get   */

@RestController
@RequestMapping("texas")
public class TexasController {

    private static final String TAG = TexasController.class.getSimpleName();

    @Autowired
    TexasService texasService;

    @Autowired
    UserInfo userInfo;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public void test(final HttpServletRequest request) {

        UserInfo userInfo = texasService.findUserInfo("op4n74h-hsa7qi_p7B5oYsOI85_E");
        System.out.println(TAG+"---Rayn userInfo:"+userInfo);

    }

    /**    ---Rayn 登录完就要过来存DB   */
    /**
     * Rayn
     * 1.app.js里面 获得授权之后
     * 2.
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public void login(final HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println(TAG+"---Rayn login");
        userInfo.setNickname(request.getParameter("nickName"));
        userInfo.setGender(request.getParameter("gender"));
        userInfo.setAvatarurl(request.getParameter("avatarUrl"));
        userInfo.setCountry(request.getParameter("country"));
        userInfo.setCity(request.getParameter("city"));
        userInfo.setProvince(request.getParameter("province"));
        userInfo.setLanguage(request.getParameter("language"));

        String code = request.getParameter("code");
        String session = texasService.findSession(code);
        JSONObject jsonObject = JSON.parseObject(session);
        String openid = (String) jsonObject.get("openid");
        userInfo.setOpenid(openid);

        System.out.println(TAG+"---Rayn userInfo:"+userInfo);

        texasService.saveUserInfo(userInfo);
    }

    /**    ---Rayn 获取每次游戏的参数   */
    /**
     * Rayn
     * 获取每次游戏的参数
     *  1.先在数据库里手动填写：日期和其他参数
     *  2.server通过日期去DB拿
     *  3.返回给客户端
     */
    @RequestMapping(value = "/conf", method = {RequestMethod.POST, RequestMethod.GET})
    public String conf(final HttpServletRequest request) {

        Conf conf = texasService.findConf();
        System.out.println(TAG+"---Rayn conf:"+conf.toString());

        return conf.toString();
    }

    @RequestMapping(value = "/session", method = {RequestMethod.POST, RequestMethod.GET})
    public String session(final HttpServletRequest request) {
        String code = request.getParameter("code");
        String session = texasService.findSession(code);
        JSONObject jsonObject = JSON.parseObject(session);
        String openid = (String) jsonObject.get("openid");
        System.out.println(TAG+"---Rayn openid:"+openid);
        return openid;
    }

}
