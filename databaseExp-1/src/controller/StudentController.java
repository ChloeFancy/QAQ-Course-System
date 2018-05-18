package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.daoImpl.AdministratorDAOImpl;
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
@RequestMapping("/student")
@CrossOrigin("http://localhost:8081")
public class StudentController extends BaseController<StudentEntity>{

    @RequestMapping(value="/updatePassWord",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse updatePassWord(StudentEntity studentEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        try{
            String data = request.getParameter("data");
            JSONObject jsonObject = JSON.parseObject(data);

            Boolean result = studentDAO.updatePassWord(jsonObject);
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
