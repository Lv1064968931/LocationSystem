package com.lvlin.location_system.service.Impl;

import com.lvlin.location_system.dao.BeaconDao;
import com.lvlin.location_system.entity.Beacon;
import com.lvlin.location_system.service.BeaconService;
import com.lvlin.location_system.template.Result;
import com.lvlin.location_system.template.ResultCode;
import com.lvlin.location_system.template.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeaconServiceImpl implements BeaconService {
    @Autowired
    private BeaconDao beaconDao;

    @Override
    public Result selectOnceDevice(String beaconName) {
        return null;
    }

    @Override
    public Result addDevice(Beacon beacon) {
        Result result = new Result();
        if(beacon.getBeaconName()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"设备名不可为空",beacon);
        }else if(beacon.getLocation()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"地址不可为空",beacon);
        }
        if(beaconDao.selectByName(beacon.getBeaconName())!=null){
            return ResultFactory.buildResult(ResultCode.FAIL,"该设备已经添加，请添加其他设备",beacon);
        }
        beaconDao.insert(beacon);
        result=ResultFactory.buildResult(ResultCode.SUCCESS,"设备添加成功！",beacon);
        return result;
    }

    @Override
    public Result deleteDevice(String beaconName) {
        Result result = new Result();
        if(beaconDao.selectByName(beaconName)==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"该设备不存在，请重新输入",null);
        }
        beaconDao.deteleByName(beaconName);
        result = ResultFactory.buildResult(ResultCode.SUCCESS,"删除成功！",null);
        return result;
    }

    @Override
    public List<Beacon> getAll() {
        return beaconDao.selectAll();
    }
}
