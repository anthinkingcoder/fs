package com.zhoulin.fileinfoserver.controller;

import com.zhoulin.fileinfoserver.config.FileInfoServerConfig;
import com.zhoulin.fileinfoserver.domain.File;
import com.zhoulin.fileinfoserver.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@Controller
public class FileController {


    @Autowired
    private FileService fileService;


    @Autowired
    private FileInfoServerConfig fileInfoServerConfig;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        File fileInfo;
        try {
            fileInfo = new File(file.getOriginalFilename(),
                    DigestUtils.md5DigestAsHex(file.getInputStream()), file.getBytes(), file.getContentType(), file.getSize());
            fileInfo = fileService.saveOne(fileInfo);
            fileInfo.setPath(fileInfoServerConfig.getUploadUrl() + "/file/upload/" + fileInfo.getId());
            return ResponseEntity.ok(fileInfo.getPath());
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 error");
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity preview(@PathVariable("id") String id) {
        File file = fileService.findOne(id);
        if (file != null) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
//                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment")
                    .body(file.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity download(@PathVariable("id") String id) {
        File file = fileService.findOne(id);
        if (file != null) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment")
                    .body(file.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }


}
