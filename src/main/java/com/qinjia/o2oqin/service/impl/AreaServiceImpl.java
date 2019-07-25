package com.qinjia.o2oqin.service.impl;

import com.qinjia.o2oqin.dao.AreaDao;
import com.qinjia.o2oqin.entity.Area;
import com.qinjia.o2oqin.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
      return areaDao.queryArea();
    }
}
