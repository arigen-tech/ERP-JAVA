package com.erp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasItemSubcategoryRes {

    private Long id;
    private String code;
    private String name;
    private String status;
    private Long itemCategoryId;
}
