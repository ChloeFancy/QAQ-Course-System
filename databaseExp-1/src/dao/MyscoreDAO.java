package dao;

import model.MyscoreEntity;
import model.TeachingEntity;

import java.util.List;

public interface MyscoreDAO {
    List<MyscoreEntity> findScoreByCourse(MyscoreEntity myscoreEntity);
}