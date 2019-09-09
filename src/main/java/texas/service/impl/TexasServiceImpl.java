package texas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import texas.bean.Conf;
import texas.bean.Constant;
import texas.bean.UserInfo;
import texas.dao.TexasDao;
import texas.service.TexasService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ---Rayn saveUserInfo find remove modify
 */

@Service
public class TexasServiceImpl implements TexasService {
    @Autowired
    TexasDao texasDao;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        texasDao.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo findUserInfo(String openid) {
        return texasDao.selectUserInfo(openid);
    }

    @Override
    public Conf findConf() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        return texasDao.selectConf(date);
    }

    @Override
    public String findSession(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret=" + Constant.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String session = restTemplate.getForObject(url,String.class);
        return session;
        /**
         * Rayn
         * 另一个参数session_key不知道用来干什么，用的时候再说
         * {
         *     "session_key":"MupMH/gwCCLNy0x8peJstQ==",
         *     "openid":"op4n74h-hsa7qi_p7B5oYsOI85_E"
         * }
         */
    }
}
