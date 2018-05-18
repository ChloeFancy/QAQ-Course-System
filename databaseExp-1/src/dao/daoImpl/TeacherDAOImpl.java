package dao.daoImpl;

import com.alibaba.fastjson.JSONObject;
import dao.TeacherDAO;
import model.StudentEntity;
import model.TeacherEntity;
import model.TeachingEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public Boolean updatePassWord(JSONObject jsonObject) {
        String oldPassword = jsonObject.getString("oldPassword");
        String tid = jsonObject.getString("tid");
        String newPassword = jsonObject.getString("newPassword");
        BaseDAOImpl<TeacherEntity> baseDAO = new BaseDAOImpl<>();
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setTid(tid);
        teacherEntity.settPassword(oldPassword);
        try{
            TeacherEntity result = baseDAO.findOne(teacherEntity);
            if(result!=null){
                System.out.println("存在");
                sessionFactory = new Configuration().configure().buildSessionFactory();
                Session s = sessionFactory.openSession();
                Transaction tx = s.beginTransaction();
                String hql = "update TeacherEntity set tPassword='"+newPassword+"' where tid="+tid;
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
