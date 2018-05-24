package controller;

import dao.daoImpl.BaseDAOImpl;
import model.AdministratorEntity;
import model.BasicResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class BaseController<T> {
    @RequestMapping(value="/add",method={RequestMethod.POST})
    public @ResponseBody
    BasicResponse add(T t, HttpServletRequest request){
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");

        BaseDAOImpl<T> baseDAO = new BaseDAOImpl<>();
        try{
            request.setCharacterEncoding("utf-8");
            if(baseDAO.insert(t).equals("success")){
                response.setResCode("1");
                response.setResMsg("success");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/logout",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse logout(T t, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        try{
            request.getSession().removeAttribute("session_id");
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/findAll",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findAll(T t, HttpServletRequest request) {

        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        BaseDAOImpl<T> admin = new BaseDAOImpl<>();
        try{
            response.setData(admin.findList(t));
            response.setResMsg(admin.countAll(t)+"");
            response.setResCode("1");
            response.setResMsg("success");

            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value="/findOne",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findOne(T t, HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        BaseDAOImpl<T> admin = new BaseDAOImpl<>();
        try{
            T tmp = admin.findOne(t);
            response.setData(tmp);
            if(tmp==null){
                //fail
                response.setResCode("0");
            }else{
                //success
                response.setResCode("1");
                response.setResMsg("success");
            }
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/update",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse update(T t, HttpServletRequest request) {
//        String requestBody = request.getParameter("requestBody");
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        BaseDAOImpl<T> admin = new BaseDAOImpl<>();
        try{
            request.setCharacterEncoding("utf-8");
            admin.update(t);
            response.setResCode("1");
            response.setResMsg("success");
            return response;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public @ResponseBody
    BasicResponse delete(T t,HttpServletRequest request) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        BaseDAOImpl<T> admin = new BaseDAOImpl<>();
        try{
            admin.delete(t);
//            response.setData(tmp);
//            if(tmp==null){
//                //fail
//                response.setResCode("0");
//            }else{
            //success
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


    @RequestMapping(value = "/test",method={RequestMethod.GET})
    public @ResponseBody
    BasicResponse test(T t,HttpServletRequest httpServletRequest){
        BasicResponse response = new BasicResponse();
        response.setResCode("222");
        response.setResMsg("test");
        return response;
    }

    @RequestMapping(value="/findByQuery",method = {RequestMethod.GET})
    public @ResponseBody
    BasicResponse findByQuery(T t) {
        BasicResponse response = new BasicResponse();
        response.setResCode("-1");
        response.setResMsg("Error");
        BaseDAOImpl<T> admin = new BaseDAOImpl<>();
        try{
            response.setData(admin.findByQuery(t));
//            response.setData(tmp);
//            if(tmp==null){
//                //fail
//                response.setResCode("0");
//            }else{
            //success
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

}
