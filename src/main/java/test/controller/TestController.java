package test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.service.TestService;

import javax.servlet.http.HttpServletRequest;



/**    ---Rayn add get   */


/**    ---Rayn 这里写@RestController才会有返回值，@Controller没有返回   */
@RestController
public class TestController {

    /**    ---Rayn 这样注入会有警告，先这么写，以后再细节   */
    @Autowired
    TestService testService;

    private static final String TAG = TestController.class.getSimpleName();

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    public void test(final HttpServletRequest request) {
        System.out.println(TAG+"---Rayn test");

        testService.save();
    }

}
