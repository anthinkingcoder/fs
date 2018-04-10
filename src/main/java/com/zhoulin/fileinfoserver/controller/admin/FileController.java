package com.zhoulin.fileinfoserver.controller.admin;

import com.zhoulin.fileinfoserver.VO.FileVO;
import com.zhoulin.fileinfoserver.VO.ResultVO;
import com.zhoulin.fileinfoserver.config.FileInfoServerConfig;
import com.zhoulin.fileinfoserver.domain.File;
import com.zhoulin.fileinfoserver.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "adminFileController")
@RequestMapping("/api/admin/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileInfoServerConfig fileInfoServerConfig;

    @RequestMapping("/all")
    public ResponseEntity<ResultVO<Page<FileVO>>> List(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size, @RequestParam(value = "searchxx",defaultValue = "") String name) {

        Pageable pageable = new PageRequest(page, size);
        Page<File> fileList = fileService.findAllByNameContaining(name, new PageRequest(page, size));
        List<FileVO> fileVOS = new ArrayList<>();
        for (File file : fileList) {
            FileVO fileVO = new FileVO();
            BeanUtils.copyProperties(file, fileVO);
            fileVO.setPath(fileInfoServerConfig.getUploadUrl() + "/views/" + file.getId());
            fileVOS.add(fileVO);
        }
        Page<FileVO> fileVOPage = new PageImpl<>(fileVOS, pageable, fileList.getTotalElements());
        return ResponseEntity.ok().body(new ResultVO<>(fileVOPage));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity download(@PathVariable("id") String id) {
        File file = fileService.findOne(id);
        if (file != null) {
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.getSize()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                    .body(file.getContent());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<ResultVO<String>> deleteOne(@RequestParam("id") String id) {
        fileService.removeOne(id);
        return ResponseEntity.ok().body(new ResultVO<String>());
    }

}
