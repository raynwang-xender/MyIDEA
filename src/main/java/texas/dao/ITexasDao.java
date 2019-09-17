package texas.dao;


import org.springframework.stereotype.Service;
import texas.bean.Conf;
import texas.bean.UserInfo;


/**    ---Rayn insert select update delete count   */

@Service
public interface ITexasDao {

    String TAG = "TexasDao";

    int insertUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String openid);

    Conf selectConf(String date);

    void createTbResult(String tbName);

    int updateResult(String openid, String tbName);

    String selectHavebuy(String openid, String tbName);

    int updateWithdraw(String openid, String chip,String tbName);

    String selectResult(String tbName);
}
