package texas.service;


import org.springframework.stereotype.Service;
import test.service.TestService;
import texas.bean.UserInfo;

@Service
public interface TexasService {

    String TAG = TestService.class.getSimpleName();

    void save(UserInfo userInfo);
}
