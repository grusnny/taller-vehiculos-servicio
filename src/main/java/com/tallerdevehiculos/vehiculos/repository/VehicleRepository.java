package com.tallerdevehiculos.vehiculos.repository;

import com.tallerdevehiculos.vehiculos.entity.Vehicle;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class VehicleRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Vehicle addVehicle  (Vehicle vehicle){
        mapper.save(vehicle);
        return vehicle;
    }

    public  Vehicle  findVehicleById (String licensePlate){
        return mapper.load(Vehicle.class,licensePlate);
    }

    public PaginatedScanList<Vehicle > findAllVehicles() {
        return mapper.scan(Vehicle .class, new DynamoDBScanExpression());
    }

    public  String deleteVehicle  (Vehicle  vehicle ){
        mapper.delete(vehicle);
        return "El vehiculo fue eliminado";
    }

    public  String editVehicle (Vehicle  vehicle ){
        mapper.save(vehicle,buildExpression(vehicle));
        return "Cambios realizados";
    }

    private DynamoDBSaveExpression buildExpression(Vehicle vehicle){
        DynamoDBSaveExpression dynamoDBSaveExpression=new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue>expectedMap= new HashMap<>();
        expectedMap.put("licensePlate",new ExpectedAttributeValue(new AttributeValue().withS(vehicle.getLicensePlate())) );
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
