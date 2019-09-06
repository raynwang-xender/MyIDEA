package texas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import texas.bean.UserInfo;
import texas.dao.TexasDao;
import org.springframework.stereotype.Service;


/**    ---Rayn insert select update delete count   */

@Service
public class TexasDaoImpl implements TexasDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(UserInfo userInfo) {

        System.out.println(TAG+"---Rayn insert方法");

        final String SQL = "INSERT INTO tb_user (`wid`,`wname`) VALUES (?,?)";

        Object[] parms = new Object[]{
                userInfo.getWid(),
                userInfo.getWname()
        };


        jdbcTemplate.update(SQL,parms);

    }
}
