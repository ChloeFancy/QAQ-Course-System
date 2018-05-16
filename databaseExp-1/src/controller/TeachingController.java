package controller;


import dao.daoImpl.teachingDAOImpl;
import model.BasicResponse;
import model.CourseEntity;
import model.TeachingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teaching")
@CrossOrigin("http://localhost:8081")
public class TeachingController extends BaseController<TeachingEntity>{



    //    http://localhost:8080/teaching/findTeachingCourseName?tid=0102&openTerm=2017-2018%20冬季
    @RequestMapping(value="/findTeachingCourseName",method = {RequestMethod.GET})
    @ResponseBody
    BasicResponse findTeachingCourseName(TeachingEntity teachingEntity){
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        teachingDAOImpl teachingDAOImpl = new teachingDAOImpl();
        try{
            List<CourseEntity> result = teachingDAOImpl.findTeachingCourseName(teachingEntity);
            if(result!=null){
                response.setResCode("1");
                response.setData(result);
                response.setResMsg("查询成功");
            }else{
                response.setResCode("0");
                response.setResMsg("查询失败");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }
}
