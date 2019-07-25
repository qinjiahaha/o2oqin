package com.qinjia.o2oqin.enums;

public enum ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    NULL_SHOPID(-38,"ShopId为空"),
    NULL_SHOP(-39,"shop信息为空"),
    INNER_ERROR(-37,"内部系统错误");

    private int state;

    public int getState() {
        return state;
    }


    public String getStateInfo() {
        return stateInfo;
    }



    private String stateInfo;
    private ShopStateEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

}
