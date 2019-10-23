package stu.napls.copdmanage.config.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yepeng Ding
 */
@Component
public class Cors {

    @Value("${cors.front-app-url}")
    private String frontAppUrl;

    public String getFrontAppUrl() {
        return frontAppUrl;
    }

}
