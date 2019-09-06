package test.service;


import org.springframework.stereotype.Service;


/**    ---Rayn save find remove modify   */

@Service
public interface TestService {
    String TAG = TestService.class.getSimpleName();

    void save();

}
