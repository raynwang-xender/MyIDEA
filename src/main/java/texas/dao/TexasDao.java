package texas.dao;


import org.springframework.stereotype.Service;
import texas.bean.Conf;
import texas.bean.UserInfo;


/**    ---Rayn insertUserInfo select update delete count   */

@Service
public interface TexasDao {

    String TAG = TexasDao.class.getSimpleName();

    void insertUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String openid);

    Conf selectConf(String date);
}
