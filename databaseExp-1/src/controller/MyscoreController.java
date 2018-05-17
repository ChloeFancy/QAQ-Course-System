package controller;


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

    @RequestMapping(value="/findByCourse",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findByQuery(MyscoreEntity t, HttpServletRequest request) {
        BasicResponse response = super.findByQuery(t,request);
        response.setResCode("-1");
        response.setResMsg("Error");
        MyscoreDAOImpl myscoreDAO = new MyscoreDAOImpl();
        try{
            response.setData(myscoreDAO.findScoreByCourse(t));

            response.setResCode("2");
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
}
