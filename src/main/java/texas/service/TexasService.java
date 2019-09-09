package texas.service;


import org.springframework.stereotype.Service;
import test.service.TestService;
import texas.bean.Conf;
import texas.bean.UserInfo;

/**    ---Rayn save find remove modify   */

@Service
public interface TexasService {

    String TAG = TexasService.class.getSimpleName();

    void saveUserInfo(UserInfo userInfo);

    UserInfo findUserInfo(String openid);

    Conf findConf();

    String findSession(String code);


}
