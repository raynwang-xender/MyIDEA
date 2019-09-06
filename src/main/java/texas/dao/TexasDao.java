package texas.dao;


import org.springframework.stereotype.Service;
import texas.bean.UserInfo;


/**    ---Rayn insert select update delete count   */

@Service
public interface TexasDao {

    String TAG = TexasDao.class.getSimpleName();

    void insert(UserInfo userInfo);

}
