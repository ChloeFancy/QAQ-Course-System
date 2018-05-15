package controller;

import dao.BaseDAO;
import dao.daoImpl.AdministratorDAOImpl;
import dao.daoImpl.BaseDAOImpl;
import model.AdministratorEntity;
import model.BasicResponse;
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

    @RequestMapping(value="/login",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse login(AdministratorEntity administratorEntity, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        AdministratorDAOImpl administratorDAOImpl = new AdministratorDAOImpl();
        try{
            AdministratorEntity result = administratorDAOImpl.login(administratorEntity.getAid()
                    ,administratorEntity.getaPassword());
            if(result!=null){
                request.getSession().setAttribute("session_id",result.getAid());
                response.setResCode("1");
                response.setData(result);
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

//    @RequestMapping(value="/delete",method={RequestMethod.GET})
//    public @ResponseBody
//    BasicResponse delete(AdministratorEntity administratorEntity, HttpServletRequest request){
//        BasicResponse response = new BasicResponse();
//        response.setResCode("-1");
//        response.setResMsg("Error");
//
//        AdministratorDAOImpl administratorDAOImpl = new AdministratorDAOImpl();
//        try{
//            administratorDAOImpl.delete(administratorEntity.getAid());
//            response.setResCode("1");
//            response.setResMsg("success");
//            return response;
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return null;
//    }


}
