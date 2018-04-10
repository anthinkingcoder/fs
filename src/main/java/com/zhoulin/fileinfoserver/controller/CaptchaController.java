package com.zhoulin.fileinfoserver.controller;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

@Slf4j
@Controller
public class CaptchaController {

    private Properties props = new Properties();

    private Producer kaptchaProducer = null;

    private String sessionKeyValue = null;

    private String sessionKeyDateValue = null;

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Switch off disk based caching.
        ImageIO.setUseCache(false);

        Enumeration<?> initParams = request.getParameterNames();
        while (initParams.hasMoreElements()) {
            String key = (String) initParams.nextElement();
            String value = request.getParameter(key);
            this.props.put(key, value);
        }

        Config config = new Config(this.props);
        this.kaptchaProducer = config.getProducerImpl();
        this.sessionKeyValue = config.getSessionKey();
        this.sessionKeyDateValue = config.getSessionDate();

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = this.kaptchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(this.sessionKeyValue, capText);

        // store the date in the session so that it can be compared
        // against to make sure someone hasn't taken too long to enter
        // their kaptcha
        request.getSession().setAttribute(this.sessionKeyDateValue, new Date());

        // create the image with the text
        BufferedImage bi = this.kaptchaProducer.createImage(capText);
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // write the data out
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
    }
}
