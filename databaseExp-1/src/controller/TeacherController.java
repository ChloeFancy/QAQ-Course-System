package controller;


import dao.daoImpl.StudentDAOImpl;
import dao.daoImpl.TeacherDAOImpl;
import model.BasicResponse;
import model.StudentEntity;
import model.TeacherEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/teacher")
@CrossOrigin("http://localhost:8081")
public class TeacherController extends BaseController<TeacherEntity>{

    @RequestMapping(value="/login",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse login(TeacherEntity teacherEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        TeacherDAOImpl administratorDAOImpl = new TeacherDAOImpl();
        try{
            TeacherEntity result = administratorDAOImpl.login(teacherEntity.getTid()
                    ,teacherEntity.gettPassword());
            if(result!=null){
                request.getSession().setAttribute("session_id",result.getTid());
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
