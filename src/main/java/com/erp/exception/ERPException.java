package com.erp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ERPException extends  RuntimeException{
    private String field;
    private Integer status;

    public ERPException(String field,Integer status,String msg){
        super(msg);
        this.field=field;
        this.status=status;
    }
    public ERPException(Integer status,String msg){
        super(msg);
        this.status=status;
    }
}
