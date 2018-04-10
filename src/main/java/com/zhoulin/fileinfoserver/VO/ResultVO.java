package com.zhoulin.fileinfoserver.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String errorMsg;
    private T data;


    public ResultVO(Integer code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ResultVO(T data) {
        this.code = 200;
        this.data = data;
        this.errorMsg = "";
    }

    public ResultVO() {
        this(null);
    }
}
