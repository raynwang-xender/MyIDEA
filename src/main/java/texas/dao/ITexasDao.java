package texas.dao;


import org.springframework.stereotype.Service;
import texas.bean.Conf;
import texas.bean.UserInfo;


/**    ---Rayn insertUserInfo select update delete count   */

@Service
public interface ITexasDao {

    String TAG = "TexasDao";

    void insertUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String openid);

    Conf selectConf(String date);
}
