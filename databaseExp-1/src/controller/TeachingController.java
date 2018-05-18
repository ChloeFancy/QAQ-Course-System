package controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.daoImpl.teachingDAOImpl;
import model.BasicResponse;
import model.CourseEntity;
import model.TeachingEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/teaching")
@CrossOrigin("http://localhost:8081")
public class TeachingController extends BaseController<TeachingEntity>{

    //    http://localhost:8080/teaching/findTeachingCourseName?tid=0102&openTerm=2017-2018%20冬季
    @RequestMapping(value="/findTeachingCourseName",method = {RequestMethod.GET})
    @ResponseBody
    BasicResponse findTeachingCourseName(TeachingEntity teachingEntity){
        //老师-查看自己在某学期教的课程

        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        teachingDAOImpl teachingDAOImpl = new teachingDAOImpl();
        try{
            JSONArray result = teachingDAOImpl.findTeachingCourseName(teachingEntity);
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

    @RequestMapping(value="/findAllDetail",method = {RequestMethod.GET})
    @ResponseBody
    BasicResponse findAllDetail(TeachingEntity teachingEntity){
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        teachingDAOImpl teachingDAOImpl = new teachingDAOImpl();
        try{
            JSONArray result = teachingDAOImpl.findAllDetail(teachingEntity);
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

    @RequestMapping(value="/findAllDetailByQuery",method = {RequestMethod.POST})
    @ResponseBody
    BasicResponse findAllDetailByQuery(HttpServletRequest httpServletRequest){
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        teachingDAOImpl teachingDAOImpl = new teachingDAOImpl();
        try{
            String json = httpServletRequest.getParameter("data");
            System.out.println(json);
            JSONArray result = teachingDAOImpl.findAllDetailByQuery(JSONObject.parseObject(json));
//            if(result!=null){
                response.setResCode("1");
                response.setData(result);
                response.setResMsg("查询成功");
//            }else{
//                response.setResCode("0");
//                response.setResMsg("查询失败");
//            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }



}
