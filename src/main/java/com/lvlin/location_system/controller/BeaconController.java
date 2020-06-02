package com.lvlin.location_system.controller;

import com.lvlin.location_system.entity.Beacon;
import com.lvlin.location_system.service.Impl.BeaconServiceImpl;
import com.lvlin.location_system.template.Result;
import com.lvlin.location_system.template.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BeaconController {
    @Autowired
    private BeaconServiceImpl beaconServiceImpl;

    //添加设备方法
    @CrossOrigin
    @RequestMapping(value = "/api/add_device",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result login(@Valid @RequestBody() Beacon beacon, BindingResult bindingResult){
        Result result= beaconServiceImpl.addDevice(beacon);
        if(bindingResult.hasErrors()){
            String message = String.format("添加失败，详细信息[%s]",bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        return result;
    }

    //查询所有设备
    @CrossOrigin
    @RequestMapping(value = "/api/select_all_device",produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public List<Beacon> selectAllDevice(){
        return beaconServiceImpl.getAll();
    }

    //删除一个设备
    @CrossOrigin
    @RequestMapping(value = "/api/delete_device",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result delete(@RequestParam("beaconName") String beaconName){
        Result result = beaconServiceImpl.deleteDevice(beaconName);
        return result;
    }
}
