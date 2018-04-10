package com.zhoulin.fileinfoserver.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@ConfigurationProperties(prefix = "ueditor")
@Component
@Data
public class UeditorConfig {
    private Map<String, Object> config;
    private String[] fileManagerAllowFiles;
    private String[] catcherAllowFiles;
    private String[] videoAllowFiles;
    private String[] fileAllowFiles;
    private String[] imageManagerAllowFiles;
    private String[] catcherLocalDomain;

    public Map<String, Object> getAllConfig() {
        config.put("fileManagerAllowFiles", fileManagerAllowFiles);
        config.put("catcherAllowFiles", catcherAllowFiles);
        config.put("videoAllowFiles", videoAllowFiles);
        config.put("fileAllowFiles", fileAllowFiles);
        config.put("imageManagerAllowFiles", imageManagerAllowFiles);
        config.put("catcherLocalDomain",catcherLocalDomain);
        return config;
    }
}
