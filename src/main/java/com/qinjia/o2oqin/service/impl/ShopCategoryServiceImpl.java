package com.qinjia.o2oqin.service.impl;

import com.qinjia.o2oqin.dao.ShopCategoryDao;
import com.qinjia.o2oqin.entity.ShopCategory;
import com.qinjia.o2oqin.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
