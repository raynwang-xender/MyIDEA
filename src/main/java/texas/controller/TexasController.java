package texas.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import texas.bean.Conf;
import texas.bean.UserInfo;
import texas.service.ITexasService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**    ---Rayn add get   */

@RestController
@RequestMapping("texas")
public class TexasController {

    private static final String TAG = TexasController.class.getSimpleName();

    @Autowired
    ITexasService texasService;

    @Autowired
    UserInfo userInfo;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public String test(final HttpServletRequest request) {
        System.out.println(TAG+"---Rayn userInfo:");
        return texasService.findUserInfo("testid").toString();
    }

    /**    ---Rayn 登录完就要过来存DB   */
    /**
     * Rayn
     * 1.app.js里面 获得授权之后
     * 2.
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(final HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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

        if (texasService.saveUserInfo(userInfo) == 1){
            return openid;
        }
        return null;
    }

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

    @RequestMapping(value = "/pay", method = {RequestMethod.POST, RequestMethod.GET})
    public boolean pay(final HttpServletRequest request) {
        String openid = request.getParameter("openid");
        return texasService.saveResult(openid) == 1;
    }

    @RequestMapping(value = "/havebuy", method = {RequestMethod.POST, RequestMethod.GET})
    public String havebuy(final HttpServletRequest request) {
        String openid = request.getParameter("openid");
        return texasService.findHavebuy(openid);
    }

    /**    ---Rayn 赎回，直接update，如果一次都没有买过，赎回按钮不可点击   */
    @RequestMapping(value = "/withdraw", method = {RequestMethod.POST, RequestMethod.GET})
    public boolean withdraw(final HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String chip = request.getParameter("chip");
        return texasService.saveWithdraw(openid,chip) == 1;
    }

    /**    ---Rayn 不是客户端调的，直接用浏览器调用，然后回显json就行   */
    @RequestMapping(value = "/settle", method = {RequestMethod.POST, RequestMethod.GET})
    public String settle(final HttpServletRequest request) {
        /**    ---Rayn 这个方法要把回显的json都拿到   */
        return texasService.findSettle();
    }


}
