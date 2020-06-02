package com.lvlin.location_system.service;

import com.lvlin.location_system.entity.Beacon;
import com.lvlin.location_system.template.Result;

import java.util.List;

public interface BeaconService {
    Result selectOnceDevice(String beaconName);
    Result addDevice(Beacon beacon);
    Result deleteDevice(String beaconName);
    List<Beacon> getAll();
}
