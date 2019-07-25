package com.qinjia.o2oqin.service;

import com.qinjia.o2oqin.dao.ShopDao;
import com.qinjia.o2oqin.dto.ShopExecution;
import com.qinjia.o2oqin.enums.ShopStateEnum;
import com.qinjia.o2oqin.service.impl.ShopServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.qinjia.o2oqin.BaseTest;
import com.qinjia.o2oqin.entity.Area;
import com.qinjia.o2oqin.entity.PersonInfo;
import com.qinjia.o2oqin.entity.Shop;
import com.qinjia.o2oqin.entity.ShopCategory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class ShopServiceTest extends BaseTest{
    @Autowired
    private ShopService shopService;

   @Test
   public void testInsertShop() throws FileNotFoundException {
       Shop shop = new Shop();
       PersonInfo owner = new PersonInfo();
       Area area = new Area();
       ShopCategory shopCategory = new ShopCategory();
       owner.setUserId(1L);
       area.setAreaId(2);
       shop.setShopCategory(shopCategory);
       shop.setOwner(owner);
       shop.setArea(area);
       shop.setShopName("覃佳的店铺3");
       shop.setShopDesc("test1");
       shop.setShopAddr("test1");
       shop.setPhone("test1");
       shop.setCreateTime(new Date());
       shop.setEnableStatus(ShopStateEnum.CHECK.getState());
       shop.setAdvice("审核中");
       File shopImg = new File("G:\\guanlan\\xiandao.jpg");
       InputStream inputStream=new FileInputStream(shopImg);
       ShopExecution shopExecution=shopService.addShop(shop,inputStream,shopImg.getName());
       assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
   }

}
