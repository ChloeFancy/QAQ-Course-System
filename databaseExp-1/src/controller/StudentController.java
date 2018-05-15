package controller;

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

    @RequestMapping(value="/login",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse login(StudentEntity studentEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        StudentDAOImpl administratorDAOImpl = new StudentDAOImpl();
        try{
            StudentEntity result = administratorDAOImpl.login(studentEntity.getSid()
                    ,studentEntity.getsPassword());
            if(result!=null){
                request.getSession().setAttribute("session_id",result.getSid());
                response.setData(result);
                response.setResCode("1");
                response.setResMsg("登录成功");
            }else{
                response.setResCode("0");
                response.setResMsg("登录失败");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

}