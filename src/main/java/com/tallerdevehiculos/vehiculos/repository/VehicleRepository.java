package com.tallerdevehiculos.vehiculos.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.tallerdevehiculos.vehiculos.config.FirebaseConfig;
import com.tallerdevehiculos.vehiculos.entity.Vehicle;


import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class VehicleRepository {

    private static FirebaseConfig firebase= new FirebaseConfig();
    private static Firestore db=firebase.getInstance();
    public static VehicleRepository repository= null;

    public static VehicleRepository getInstance() {
        if (repository == null){
            repository = new VehicleRepository();
        }
        return repository;
    }


    public Vehicle addVehicle  (Vehicle vehicle){
        DocumentReference docRef = db.collection("vehicle").document(vehicle.getLicensePlate());

        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(vehicle);
        // ...
        // result.get() blocks on response
        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return vehicle;
    }

    public  Vehicle  findVehicleById (String licensePlate){
        Vehicle vehicle=null;
        DocumentReference docRef = db.collection("vehicle").document(licensePlate);
// asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
// ...
// future.get() blocks on response
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (document.exists()) {
            // convert document to POJO
            vehicle = document.toObject(Vehicle.class);
            return vehicle;
        }
        return  vehicle;
    }

    public List<Vehicle> findAllVehicles() {
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("vehicle").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Vehicle> lista = new ArrayList<Vehicle>();

        for (DocumentSnapshot document : documents)
        {
            lista.add(document.toObject(Vehicle.class));
        }
        return lista;
    }

    public  String deleteVehicle  (Vehicle  vehicle ){

        ApiFuture<WriteResult> writeResult = db.collection("vehicle").document(vehicle.getLicensePlate()).delete();

        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "{" +
                    "\"status\"" +":\"0\""+
                    "}";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "{" +
                    "\"status\"" +":\"0\""+
                    "}";
        }

        return "{" +
                "\"status\"" +":\"1\""+
                "}";
    }

    public  String editVehicle (Vehicle  vehicle ){
        DocumentReference docRef = db.collection("vehicle").document(vehicle.getLicensePlate());

        Map<String, Object> updates = new HashMap<>();
        updates.put("type", vehicle.getType());
        updates.put("mark",vehicle.getMark());
        updates.put("color",vehicle.getColor());
// (async) Update one field
        ApiFuture<WriteResult> future = docRef.update(updates);

// ...
        WriteResult result = null;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "{" +
                    "\"status\"" +":\"0\""+
                    "}";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "{" +
                    "\"status\"" +":\"0\""+
                    "}";
        }
        return "{" +
                "\"status\"" +":\"1\""+
                "}";
    }


}
