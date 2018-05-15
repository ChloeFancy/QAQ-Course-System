package dao;

import model.ScoreEntity;

import java.util.List;

public interface ScoreDAO {
    List<Object> findSomeCourse(ScoreEntity scoreEntity);
    List<Object> findSomeScore(ScoreEntity scoreEntity);
}
