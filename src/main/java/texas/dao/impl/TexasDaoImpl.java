package texas.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import texas.bean.Conf;
import texas.bean.Result;
import texas.bean.UserInfo;
import texas.dao.ITexasDao;
import org.springframework.stereotype.Service;

import java.util.List;


/**    ---Rayn insert select update delete count   */

@Service
public class TexasDaoImpl implements ITexasDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        System.out.println(TAG+"---Rayn insertUserInfo");

        final String SQL = "insert into tb_user (openid,nickname,gender,avatarurl,country,city,province,`language`) value (?,?,?,?,?,?,?,?)" +
                "on DUPLICATE KEY UPDATE nickname=VALUES(nickname),gender=VALUES(gender),avatarurl=VALUES(avatarurl),country=VALUES(country),city=VALUES(city),province=VALUES(province),`language`=VALUES(`language`)";

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

        return jdbcTemplate.update(SQL, parms);
    }

    @Override
    public UserInfo selectUserInfo(String openid) {
        final String SQL = "select * from tb_user where openid=?";

        List<UserInfo> list = jdbcTemplate.query(SQL, new Object[]{openid}, new BeanPropertyRowMapper<>(UserInfo.class));

        if (null != list && list.size() > 0) {
            UserInfo userInfo = list.get(0);
            return userInfo;
        }

        return null;
    }

    @Override
    public Conf selectConf(String date) {
        final String SQL = "select `date`,totalbuy,chip,money,times,enable from tb_conf where `date`=?";

        List<Conf> list = jdbcTemplate.query(SQL, new Object[]{date}, new BeanPropertyRowMapper<>(Conf.class));

        if (null != list && list.size() > 0) {
            for (Conf conf: list) {
                if ("1".equals(conf.getEnable())){
                    return conf;
                }
            }
        }

        return null;
    }

    @Override
    public void createTbResult(String tbName) {
        final String SQL = "CREATE TABLE if not exists " + tbName +
                "  (openid varchar(40) NOT NULL," +
                "  chip varchar(10) NULL," +
                "  havebuy char(1) NULL," +
                "  PRIMARY KEY (openid));";

        jdbcTemplate.execute(SQL);

    }

    /**
     * Rayn
     * 购买的时候，往结果表里写
     * 先查，表里有没有，没有就insert
     * 有就update
     */
    @Override
    public int updateResult(String openid, String tbName) {
        final String SQL = "select * from "+tbName+" where openid=?";
        List<Result> list = jdbcTemplate.query(SQL, new Object[]{openid}, new BeanPropertyRowMapper<>(Result.class));
        if (null != list && list.size() > 0) {
            /**    ---Rayn 如果有，就update   */
            int havebuy = Integer.parseInt(list.get(0).getHavebuy())+1;
            final String SQL2 = "update "+tbName+" set havebuy=? where openid=?";
            return jdbcTemplate.update(SQL2, new Object[]{havebuy,openid});
        }else {
            /**    ---Rayn 如果没有，就insert   */
            final String SQL3 = "insert into "+tbName+" (openid,havebuy) values (?,'1')";
            return jdbcTemplate.update(SQL3, new Object[]{openid});
        }
    }

    @Override
    public String selectHavebuy(String openid, String tbName) {
        final String SQL = "select * from "+tbName+" where openid=?";
        List<Result> list = jdbcTemplate.query(SQL, new Object[]{openid}, new BeanPropertyRowMapper<>(Result.class));
        if (null != list && list.size() > 0) {
            return list.get(0).getHavebuy();
        }
        return null;
    }

    /**    ---Rayn 赎回   */
    @Override
    public int updateWithdraw(String openid, String chip, String tbName) {
        final String SQL = "update "+tbName+" set chip=? where openid=?";
        return jdbcTemplate.update(SQL, new Object[]{chip,openid});
    }

    /**    ---Rayn 查询Result表的所有东西   */
    @Override
    public String selectResult(String tbName) {
        int theoryChip = 0;
        int practiceChip = 0;
        int totalbuy = 0;
        final String SQL = "select * from "+tbName;
        List<Result> list = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Result.class));
        if (null != list && list.size() > 0) {
            for (Result result:list) {
                totalbuy += Integer.parseInt(result.getHavebuy());
                practiceChip += Integer.parseInt(result.getChip());
            }

//            theoryChip = totalbuy *
        }
        return null;
    }


}
