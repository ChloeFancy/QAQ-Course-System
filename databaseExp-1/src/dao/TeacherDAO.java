package dao;

import model.TeacherEntity;

public interface TeacherDAO {
    TeacherEntity login(String id,String password);
}
