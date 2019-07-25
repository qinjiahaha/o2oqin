package com.qinjia.o2oqin.dao;

import com.qinjia.o2oqin.BaseTest;
import com.qinjia.o2oqin.entity.Area;
import com.qinjia.o2oqin.entity.PersonInfo;
import com.qinjia.o2oqin.entity.Shop;
import com.qinjia.o2oqin.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

   /* @Test

   public void testInsertShop(){
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopName("覃佳的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum=shopDao.insertShop(shop);
        assertEquals(1,effectedNum);

    }*/

    @Test
    public void testUpdateShop(){
        Shop shop=new Shop();
        shop.setShopId(7L);
        shop.setShopDesc("测试描述");
        shop.setShopAddr("测试店址");
        shop.setLastEditTime(new Date());
        int effectedNum=shopDao.updateShop(shop);

        assertEquals(1,effectedNum);

    }
}
