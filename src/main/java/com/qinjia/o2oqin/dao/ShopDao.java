package com.qinjia.o2oqin.dao;

import com.qinjia.o2oqin.entity.Shop;

public interface ShopDao {
    Shop queryByShopId(long shopId);
    int insertShop(Shop shop);
    int updateShop(Shop shop);
}
