package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.AdministratorDAO;
import dao.BaseDAO;
import dao.daoImpl.AdministratorDAOImpl;
import dao.daoImpl.BaseDAOImpl;
import dao.daoImpl.StudentDAOImpl;
import model.AdministratorEntity;
import model.BasicResponse;
import model.StudentEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/administrator")
@CrossOrigin("http://localhost:8081")
public class AdministratorController extends BaseController<AdministratorEntity>{

    @RequestMapping(value="/updatePassWord",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse updatePassWord(AdministratorEntity administratorEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        AdministratorDAOImpl administratorDAO = new AdministratorDAOImpl();
        try{
            String data = request.getParameter("data");
            JSONObject jsonObject = JSON.parseObject(data);

            Boolean result = administratorDAO.updatePassWord(jsonObject);
            if(result){
                System.out.println("修改成功");
                response.setResCode("1");
                response.setResMsg("修改成功");
            }else{
                response.setResCode("-1");
                response.setResMsg("修改失败");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

}
