package com.qinjia.o2oqin.dao;

import com.qinjia.o2oqin.BaseTest;
import com.qinjia.o2oqin.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> areaList=areaDao.queryArea();
        assertEquals(2,areaList.size());
    }
}
