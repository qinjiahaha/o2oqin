package com.qinjia.o2oqin.service;

import com.qinjia.o2oqin.dto.ShopExecution;
import com.qinjia.o2oqin.entity.Shop;
import com.qinjia.o2oqin.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) throws ShopOperationException;
}
