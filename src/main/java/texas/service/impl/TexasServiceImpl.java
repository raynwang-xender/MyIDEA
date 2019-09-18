package texas.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import texas.bean.Conf;
import texas.bean.Constant;
import texas.bean.Result;
import texas.bean.UserInfo;
import texas.dao.ITexasDao;
import texas.dao.impl.TexasDaoImpl;
import texas.service.ITexasService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ---Rayn saveUserInfo find remove modify
 */

@Service
public class TexasServiceImpl implements ITexasService {
    @Autowired
    ITexasDao texasDao;

    @Autowired
    RestTemplate restTemplate;

    /**    ---Rayn 第一次存数据库   */
    @Override
    public int saveUserInfo(UserInfo userInfo) {
        return texasDao.insertUserInfo(userInfo);
    }

    /**    ---Rayn 通过openid查找UserInfo   */
    @Override
    public UserInfo findUserInfo(String openid) {
        return texasDao.selectUserInfo(openid);
    }

    @Override
    public String findSession(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + Constant.APPID + "&secret=" + Constant.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String session = restTemplate.getForObject(url,String.class);
        return session;
        /**
         * Rayn
         * 另一个参数session_key不知道用来干什么，每次都不一样
         * {
         *     "session_key":"MupMH/gwCCLNy0x8peJstQ==",
         *     "openid":"op4n74h-hsa7qi_p7B5oYsOI85_E"
         * }
         */
    }

    /**    ---Rayn 查找今天的对局参数   */
    @Override
    public Conf findConf() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDateFormat.format(new Date());
        /**    ---Rayn 通过date查询 可能不止一次 查到之后看状态是否结束   */
        Conf conf = texasDao.selectConf(date);//conf可能是空 。。。。。。。。。。。。。。。。。。。。。。
        String tbName = "tb_result_"+date+"_"+conf.getTimes();
        /**    ---Rayn 顺便建个表 tb_result_20190915_1   */
        texasDao.createTbResult(tbName);
        return conf;
    }

    @Override
    public int saveResult(String openid) {
        Conf conf = findConf();
        String tbName = "tb_result_"+conf.getDate()+"_"+conf.getTimes();
        return texasDao.updateResult(openid,tbName);
    }

    @Override
    public String findHavebuy(String openid) {
        Conf conf = findConf();
        String tbName = "tb_result_"+conf.getDate()+"_"+conf.getTimes();
        return texasDao.selectHavebuy(openid, tbName);
    }

    @Override
    public int saveWithdraw(String openid, String chip) {
        Conf conf = findConf();
        String tbName = "tb_result_"+conf.getDate()+"_"+conf.getTimes();
        return texasDao.updateWithdraw(openid,chip,tbName);
    }

    @Override
    public String findSettle() {
        int theoryChip = 0;
        int practiceChip = 0;
        int totalbuy = 0;
        float money_stable;
        int chip_stable;//每手多少筹码
        float price;//单价 每个筹码多少钱

        Conf conf = findConf();
        String tbName = "tb_result_"+conf.getDate()+"_"+conf.getTimes();
        money_stable = Float.parseFloat(conf.getMoney());
        chip_stable = Integer.parseInt(conf.getChip());
        price = money_stable/chip_stable;
        System.out.println(TAG+"---Rayn price:"+price + " "+money_stable + " " +chip_stable);
        JSONObject userJson = new JSONObject();
        JSONObject stateJson = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        /**    ---Rayn 本次对局的所有人 筹码 买的次数 集合   */
        List<Result> results = texasDao.selectResult(tbName);
        for (Result result:results) {
            int havebuy = Integer.parseInt(result.getHavebuy());
            totalbuy += havebuy;
            int chip = Integer.parseInt(result.getChip());
            practiceChip += chip;
            String openid = result.getOpenid();
            UserInfo userInfo = texasDao.selectUserInfo(openid);
            userJson.put(userInfo.getNickname(),chip * price);
        }
        theoryChip = totalbuy * chip_stable;

        stateJson.put("是否配平",theoryChip == practiceChip);
        if (theoryChip > practiceChip) {
            stateJson.put("筹码多余",theoryChip - practiceChip);
        }else if (theoryChip < practiceChip) {
            stateJson.put("筹码不足",practiceChip - theoryChip);
        }

        jsonObject.put("state",stateJson);
        jsonObject.put("users",userJson);



        return jsonObject.toString();

    }

}
