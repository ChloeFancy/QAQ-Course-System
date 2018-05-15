package dao.daoImpl;

import dao.TeacherDAO;
import model.StudentEntity;
import model.TeacherEntity;
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
    public TeacherEntity login(String id, String password) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "from TeacherEntity as a where a.tid=? and a.tPassword=?";
        Query query = s.createQuery(hql);
        query.setParameter(0,id);
        query.setParameter(1,password);

        List list = query.list();
        Iterator iterator = list.iterator();

        TeacherEntity teacherEntity=null;
        while(iterator.hasNext()){
            teacherEntity = (TeacherEntity)iterator.next();
        }
        return teacherEntity;
    }
}
