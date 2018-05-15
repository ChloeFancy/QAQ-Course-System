package dao;

import model.StudentEntity;

public interface StudentDAO {
    StudentEntity login(String id, String password);
}
