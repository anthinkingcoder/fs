package com.zhoulin.fileinfoserver.ueditor;

import com.zhoulin.fileinfoserver.config.FileInfoServerConfig;
import com.zhoulin.fileinfoserver.config.UeditorConfig;
import com.zhoulin.fileinfoserver.domain.File;
import com.zhoulin.fileinfoserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
public class Ueditors {
    @Autowired
    private UeditorConfig ueditorConfig;

    @Autowired
    private FileInfoServerConfig fileInfoServerConfig;

    @Autowired
    private FileService fileService;

    public Map<String, Object> exec(String action, Integer start, Integer size, MultipartFile file) {
        Map<String, Object> responses = new HashMap<>(15);
        responses.put("state", "SUCCESS");
        responses.put("url", "upload/demo.zip");
        try {
            switch (ActionEnum.of(action)) {
                case CONFIG:
                    return ueditorConfig.getAllConfig();
                case UPLOAD_IMAGE:
                    File fileInfo = new File(file.getOriginalFilename(),
                            DigestUtils.md5DigestAsHex(file.getInputStream()), file.getBytes(), file.getContentType(), file.getSize());
                    fileInfo = fileService.saveOne(fileInfo);
                    if (fileInfo != null) {
                        responses.put("state", "SUCCESS");
                        responses.put("url", fileInfoServerConfig.getUploadUrl() + "/view/" + fileInfo.getId());
                        responses.put("title", file.getName());
                        responses.put("original", file.getName());
                    }
                    return responses;
                default:
                    break;
            }
        } catch (Exception e) {
            return null;
        }
        return responses;
    }
}
