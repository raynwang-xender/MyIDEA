package texas.service;


import org.springframework.stereotype.Service;
import texas.bean.Conf;
import texas.bean.UserInfo;

/**    ---Rayn save find remove modify   */

@Service
public interface ITexasService {

    String TAG = "TexasService";

    int saveUserInfo(UserInfo userInfo);

    UserInfo findUserInfo(String openid);

    Conf findConf();

    String findSession(String code);

    int saveResult(String openid);

    String findHavebuy(String openid);

    int saveWithdraw(String openid, String chip);

    String findSettle();

}
