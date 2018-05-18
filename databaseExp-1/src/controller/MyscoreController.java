package controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.daoImpl.BaseDAOImpl;
import dao.daoImpl.MyscoreDAOImpl;
import model.BasicResponse;
import model.MyscoreEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Myscore")
@CrossOrigin("http://localhost:8081")
public class MyscoreController extends BaseController<MyscoreEntity> {

    @RequestMapping(value="/deleteByStudent",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse deleteByStudent(MyscoreEntity t, HttpServletRequest request) {
        //学生退课，有成绩就不能删
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        MyscoreDAOImpl myscoreDAO = new MyscoreDAOImpl();
        try{
            if(myscoreDAO.isDeletable(t)){
                super.delete(t,request);
                response.setResCode("1");
                response.setResMsg("success");
            }
            return response;
        }catch(Exception ex){
            response.setResCode("-1");
            response.setResMsg("Error");
            ex.printStackTrace();
        }

        return response;
    }


    @RequestMapping(value="/deleteByTeacher",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse deleteByTeacher(MyscoreEntity t, HttpServletRequest request) {

        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");

        BaseDAOImpl<MyscoreEntity> baseDAO = new BaseDAOImpl<>();
        try{
            baseDAO.delete(t);
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            response.setResCode("-1");
            response.setResMsg("Error");
            ex.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value="/findByCourse",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findByCourse(MyscoreEntity t, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        MyscoreDAOImpl myscoreDAO = new MyscoreDAOImpl();
        try{
            response.setData(myscoreDAO.findScoreByCourse(t));

            response.setResCode("1");
            response.setResMsg("success");
//            }
            return response;
        }catch(Exception ex){
            response.setResCode("-1");
            response.setResMsg("Error");
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/findScoreForStudent",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findScoreForStudent(MyscoreEntity t, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        MyscoreDAOImpl myscoreDAO = new MyscoreDAOImpl();
        try{
            response.setData(myscoreDAO.findScoreForStudent(t));
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            response.setResCode("-1");
            response.setResMsg("Error");
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/findCourseForStudent",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findCourseForStudent(MyscoreEntity t, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        MyscoreDAOImpl myscoreDAO = new MyscoreDAOImpl();
        try{
            response.setData(myscoreDAO.findCourseForStudent(t));
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            response.setResCode("-1");
            response.setResMsg("Error");
            ex.printStackTrace();
        }
        return response;
    }
}
