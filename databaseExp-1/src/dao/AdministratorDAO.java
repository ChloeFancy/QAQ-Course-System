package dao;

import model.AdministratorEntity;

import java.util.List;

public interface AdministratorDAO {
//     void add(AdministratorEntity administratorEntity);
     void delete(String id);
     AdministratorEntity login(String id, String password);
//     List<AdministratorEntity> getAll();
}



