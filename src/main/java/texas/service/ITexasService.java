package texas.service;


import org.springframework.stereotype.Service;
import texas.bean.Conf;
import texas.bean.UserInfo;

/**    ---Rayn save find remove modify   */

@Service
public interface ITexasService {

    String TAG = "TexasService";

    void saveUserInfo(UserInfo userInfo);

    UserInfo findUserInfo(String openid);

    Conf findConf();

    String findSession(String code);


}
