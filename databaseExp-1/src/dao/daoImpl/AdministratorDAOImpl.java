package dao.daoImpl;

import com.alibaba.fastjson.JSONObject;
import dao.AdministratorDAO;
import model.AdministratorEntity;
import model.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Transactional
public class AdministratorDAOImpl implements AdministratorDAO {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Boolean updatePassWord(JSONObject jsonObject) {
        String oldPassword = jsonObject.getString("oldPassword");
        String sid = jsonObject.getString("aid");
        String newPassword = jsonObject.getString("newPassword");
        BaseDAOImpl<AdministratorEntity> baseDAO = new BaseDAOImpl<>();
        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setAid(sid);
        administratorEntity.setaPassword(oldPassword);
        try{
            AdministratorEntity result = baseDAO.findOne(administratorEntity);
            if(result!=null){
                System.out.println("存在");
                sessionFactory = new Configuration().configure().buildSessionFactory();
                Session s = sessionFactory.openSession();
                Transaction tx = s.beginTransaction();
                String hql = "update AdministratorEntity set aPassword='"+newPassword+"' where aid="+sid;
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
