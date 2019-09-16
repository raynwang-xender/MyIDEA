package texas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import texas.bean.Conf;
import texas.bean.UserInfo;
import texas.dao.ITexasDao;
import org.springframework.stereotype.Service;

import java.util.List;


/**    ---Rayn insertUserInfo select update delete count   */

@Service
public class TexasDaoImpl implements ITexasDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        System.out.println(TAG+"---Rayn insertUserInfo");

        final String SQL = "insert into tb_user (`openid`,`nickname`,`gender`,`avatarurl`,`country`,`city`,`province`,`language`) value (?,?,?,?,?,?,?,?)";

        Object[] parms = new Object[]{
                userInfo.getOpenid(),
                userInfo.getNickname(),
                userInfo.getGender(),
                userInfo.getAvatarurl(),
                userInfo.getCountry(),
                userInfo.getCity(),
                userInfo.getProvince(),
                userInfo.getLanguage(),
        };

        try {
            jdbcTemplate.update(SQL, parms);
        }catch (Exception e){

        }
    }

    @Override
    public UserInfo selectUserInfo(String openid) {
        final String SQL = "select * from tb_user where `openid`=?";

        List<UserInfo> list = jdbcTemplate.query(SQL, new Object[]{openid}, new BeanPropertyRowMapper<>(UserInfo.class));

        if (null != list && list.size() > 0) {
            UserInfo userInfo = list.get(0);
            return userInfo;
        }

        return null;
    }

    @Override
    public Conf selectConf(String date) {
        final String SQL = "select `date`,`totalbuy`,`chip`,`money` from tb_conf where `date`=?";

        List<Conf> list = jdbcTemplate.query(SQL, new Object[]{date}, new BeanPropertyRowMapper<>(Conf.class));

        if (null != list && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

}
