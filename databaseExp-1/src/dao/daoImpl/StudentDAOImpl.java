package dao.daoImpl;

import dao.StudentDAO;
import model.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public StudentEntity login(String id, String password) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from StudentEntity as a where a.sid=? and a.sPassword=?";
        Query query = s.createQuery(hql);
        query.setParameter(0,id);
        query.setParameter(1,password);

        List list = query.list();
        Iterator iterator = list.iterator();

        StudentEntity studentEntity=null;
        while(iterator.hasNext()){
            studentEntity = (StudentEntity)iterator.next();
        }
        return studentEntity;

    }
}
