package com.qinjia.o2oqin.dao;

import com.qinjia.o2oqin.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")
                                         ShopCategory shopCategoryCondition);
}
