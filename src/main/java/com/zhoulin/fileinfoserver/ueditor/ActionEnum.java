package com.zhoulin.fileinfoserver.ueditor;

public enum ActionEnum {
    /**
     * 配置
     */
    CONFIG("config", 1),
    /**
     * 上传图片
     */
    UPLOAD_IMAGE("uploadimage", 2),
    UPLOAD_SCRAWL("uploadscrawl", 3),
    UPLOAD_VIDEO("upload_video", 4),
    UPLOAD_FILE("uploadfile", 5),
    LIST_IMAGE("listimage", 6),
    CATCH_IMAGE("catchimage", 7),;


    private String name;
    private Integer code;

    ActionEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    public static ActionEnum of(String name) {
        if (name == null) {
            return null;
        }
        if (CONFIG.name.equals(name)) {
            return CONFIG;
        } else if (UPLOAD_IMAGE.name.equals(name)) {
            return UPLOAD_IMAGE;
        } else if (UPLOAD_FILE.name.equals(name)) {
            return UPLOAD_FILE;
        } else if (UPLOAD_SCRAWL.name.equals(name)) {
            return UPLOAD_SCRAWL;
        } else if (UPLOAD_VIDEO.name.equals(name)) {
            return UPLOAD_VIDEO;
        } else if (LIST_IMAGE.name.equals(name)) {
            return LIST_IMAGE;
        } else if (CATCH_IMAGE.name.equals(name)) {
            return CATCH_IMAGE;
        } else {
            return null;
        }
    }
}
