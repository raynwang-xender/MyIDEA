package test.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import test.dao.TestDao;


/**    ---Rayn select update delete count   */

@Service
public class TestDaoImpl implements TestDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void insert() {
        System.out.println(TAG+"---Rayn insert方法");

         final String SQL = "INSERT INTO tb_test (`uid`,`name`,`gender`) VALUES (8,'123',12)";

        jdbcTemplate.execute(SQL);
    }









}
