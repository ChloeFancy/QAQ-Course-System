package dao;

import com.alibaba.fastjson.JSONObject;
import model.TeacherEntity;

public interface TeacherDAO {
    Boolean updatePassWord(JSONObject jsonObject);

}
