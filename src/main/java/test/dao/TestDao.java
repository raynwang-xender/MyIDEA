package test.dao;


import org.springframework.stereotype.Service;

/**    ---Rayn insert select update delete count   */

@Service
public interface TestDao {

    String TAG = TestDao.class.getSimpleName();

    void insert();
}
