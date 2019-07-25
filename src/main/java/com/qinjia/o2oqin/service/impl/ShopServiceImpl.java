package com.qinjia.o2oqin.service.impl;

import com.qinjia.o2oqin.dao.ShopDao;
import com.qinjia.o2oqin.dto.ShopExecution;
import com.qinjia.o2oqin.entity.Shop;
import com.qinjia.o2oqin.enums.ShopStateEnum;
import com.qinjia.o2oqin.exceptions.ShopOperationException;
import com.qinjia.o2oqin.service.ShopService;
import com.qinjia.o2oqin.util.ImageUtil;
import com.qinjia.o2oqin.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        /**空值判断*/
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);

        }
        try{
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.insertShop(shop);
            if(effectedNum<=0){
                throw new ShopOperationException("店铺创建失败");
            }else{
                if(shopImgInputStream !=null){
                    try {
                        addShopImg(shop, shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error"+e.getMessage());

                    }
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片店址失败");
                    }
                }
            }
        }catch(Exception e){
            throw new ShopOperationException("addShop error"+e.getMessage());
        }
       return new ShopExecution(ShopStateEnum.CHECK,shop);
    }



    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        String dest= PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
        shop.setShopImg(shopImgAddr);
    }
}
