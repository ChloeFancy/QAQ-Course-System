package dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import model.MyscoreEntity;
import model.TeachingEntity;

import java.util.List;

public interface MyscoreDAO {
    JSONArray findScoreByCourse(MyscoreEntity myscoreEntity);
    JSONArray findScoreForStudent(MyscoreEntity myscoreEntity);
    JSONArray findCourseForStudent(MyscoreEntity myscoreEntity);
    Boolean isDeletable(MyscoreEntity myscoreEntity);
}