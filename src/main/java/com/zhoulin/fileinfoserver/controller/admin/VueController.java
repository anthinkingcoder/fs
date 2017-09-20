package com.zhoulin.fileinfoserver.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class VueController {
    @GetMapping("/main/**")
    public String Vue() {
        log.info("vue");
        return "/index.html";

    }
}
