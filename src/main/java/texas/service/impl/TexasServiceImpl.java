package texas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import test.service.TestService;
import texas.bean.UserInfo;
import texas.dao.TexasDao;
import texas.service.TexasService;
import org.springframework.stereotype.Service;

/**    ---Rayn save find remove modify   */

@Service
public class TexasServiceImpl implements TexasService {
    @Autowired
    TexasDao texasDao;

    @Override
    public void save(UserInfo userInfo) {
        texasDao.insert(userInfo);
    }
}
