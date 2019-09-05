package test.dao.impl;


import org.springframework.stereotype.Service;
import test.dao.TestDao;


/**    ---Rayn select update delete count   */

@Service
public class TestDaoImpl implements TestDao {

    @Override
    public void insert() {
        System.out.println(TAG+"---Rayn insert方法");
    }


}
