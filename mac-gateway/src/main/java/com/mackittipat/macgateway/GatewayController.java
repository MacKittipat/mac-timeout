package com.mackittipat.macgateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/gateway")
    public String gateway() {

//        ResponseEntity<Map> responseEntity = restTemplate.exchange("http://localhost:9002/server", HttpMethod.GET, null, Map.class);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("http://localhost/server", HttpMethod.GET, null, Map.class);

        log.debug(">>> 1 = {}", responseEntity.getBody().toString());

        return "gateway";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OAuth2Exception> error(Exception ex) throws Exception {
        log.error("Error : " + ex.getMessage());
        DefaultWebResponseExceptionTranslator defaultWebResponseExceptionTranslator = new DefaultWebResponseExceptionTranslator();
        return defaultWebResponseExceptionTranslator.translate(ex);

    }

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public String error(Exception ex) {
//        log.error("Error : " + ex.getMessage());
//        return ex.getMessage();
//    }
}
