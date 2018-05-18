package dao;

import com.alibaba.fastjson.JSONObject;
import model.StudentEntity;

public interface StudentDAO {
    Boolean updatePassWord(JSONObject jsonObject);
}
