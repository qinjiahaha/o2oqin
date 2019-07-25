package com.qinjia.o2oqin.service;

import com.qinjia.o2oqin.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
