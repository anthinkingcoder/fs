package com.zhoulin.fileinfoserver.VO;

import lombok.Data;

import java.util.Date;

@Data
public class FileVO {
    private String id;
    private String name;//文件名称
    private String md5;//文件md5值
    private String contentType; //文件类型
    private String path; //访问路径
    private Date uploadTime;
    private Long size;

}
