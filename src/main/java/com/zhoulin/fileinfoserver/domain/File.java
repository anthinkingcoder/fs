package com.zhoulin.fileinfoserver.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
@Slf4j
public class File implements Serializable{
    @Id
    private String id;
    private String name;//文件名称
    private String md5;//文件md5值
    private byte[] content; //文件内容
    private String contentType; //文件类型
    private String path; //访问路径
    private Date uploadTime;
    private Long size;


    public File(String name, String md5, byte[] content, String contentType,Long size) {
        this.name = name;
        this.md5 = md5;
        this.content = content;
        this.contentType = contentType;
        this.size = size;
    }

    public File() {
    }





}
