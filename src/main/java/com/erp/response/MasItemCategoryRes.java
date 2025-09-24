package com.erp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasItemCategoryRes {

    private Long id;
    private String code;
    private String name;
    private String status;
    private Long itemGroupId;
}
