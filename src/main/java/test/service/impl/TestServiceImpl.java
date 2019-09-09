package test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dao.TestDao;
import test.service.TestService;



/**    ---Rayn saveUserInfo find remove modify   */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;

    @Override
    public void save() {
        System.out.println(TAG+"---Rayn 方法save");

        testDao.insert();

    }
}
