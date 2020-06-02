package com.lvlin.location_system.dao;

import com.lvlin.location_system.entity.Beacon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BeaconDao {

    Beacon selectByName(String beaconName);
    void insert(Beacon beacon);
    void deteleByName(String beaconName);
    List<Beacon> selectAll();
}
