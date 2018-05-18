package dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.CourseEntity;
import model.TeacherEntity;
import model.TeachingEntity;

import java.util.List;

public interface teachingDAO {
    JSONArray findTeachingCourseName(TeachingEntity teachingEntity);
    JSONArray findAllDetail(TeachingEntity teachingEntity);
    JSONArray findAllDetailByQuery(JSONObject json);
}
