package dao.daoImpl;

import com.alibaba.fastjson.JSONObject;
import dao.StudentDAO;
import model.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import util.MD5;

import java.util.Iterator;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();


    @Override
    public Boolean updatePassWord(JSONObject jsonObject) {
        String oldPassword = jsonObject.getString("oldPassword");
        String sid = jsonObject.getString("sid");
        String newPassword = jsonObject.getString("newPassword");
        BaseDAOImpl<StudentEntity> baseDAO = new BaseDAOImpl<>();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setSid(sid);
        studentEntity.setsPassword(oldPassword);
        try{
            StudentEntity result = baseDAO.findOne(studentEntity);
            if(result!=null){
                System.out.println("存在");
                sessionFactory = new Configuration().configure().buildSessionFactory();
                Session s = sessionFactory.openSession();
                Transaction tx = s.beginTransaction();
                String hql = "update StudentEntity set sPassword='"+newPassword+"' where sid="+sid;
                Query query = s.createQuery(hql);
                query.executeUpdate();
                tx.commit();
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
