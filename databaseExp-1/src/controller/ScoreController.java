package controller;

import dao.daoImpl.AdministratorDAOImpl;
import dao.daoImpl.ScoreDAOImpl;
import model.AdministratorEntity;
import model.BasicResponse;
import model.ScoreEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/score")
@CrossOrigin("http://localhost:8081")
public class ScoreController extends BaseController<ScoreEntity> {
    @RequestMapping(value="/findSomeCourse",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findSomeCourse(ScoreEntity scoreEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        ScoreDAOImpl scoreDAOImpl = new ScoreDAOImpl();
        try{
            List<Object> result = scoreDAOImpl.findSomeCourse(scoreEntity);
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

    @RequestMapping(value="/findSomeScore",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findSomeScore(ScoreEntity scoreEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        ScoreDAOImpl scoreDAOImpl = new ScoreDAOImpl();
        try{
            List<Object> result = scoreDAOImpl.findSomeScore(scoreEntity);
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
