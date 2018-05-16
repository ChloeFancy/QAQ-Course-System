package dao;

import model.CourseEntity;
import model.TeachingEntity;

import java.util.List;

public interface teachingDAO {
    List<CourseEntity> findTeachingCourseName(TeachingEntity teachingEntity);
}
