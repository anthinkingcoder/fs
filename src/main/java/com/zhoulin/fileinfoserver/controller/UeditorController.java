package com.zhoulin.fileinfoserver.controller;

import com.zhoulin.fileinfoserver.ueditor.Ueditors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/ueditor")
@Slf4j
public class UeditorController {
    @Autowired
    private Ueditors ueditors;
    @RequestMapping("/config")
    public Map<String, Object> config(String action, Integer start, Integer size, @RequestParam(required = false, name = "upfile") MultipartFile file) {
        return ueditors.exec(action, start, size, file);
    }
}
