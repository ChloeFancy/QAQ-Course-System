package dao;

import com.alibaba.fastjson.JSONObject;
import model.AdministratorEntity;

import java.util.List;

public interface AdministratorDAO {
     Boolean updatePassWord(JSONObject jsonObject);
}



