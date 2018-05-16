package dao.daoImpl;

import dao.teachingDAO;
import model.CourseEntity;
import model.TeachingEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class teachingDAOImpl implements teachingDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<CourseEntity> findTeachingCourseName(TeachingEntity teachingEntity) {
        String openrTerm = teachingEntity.getOpenTerm();
        String tid = teachingEntity.getTid();

        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "select c from TeachingEntity t,CourseEntity c where t.cid=c.cid and t.tid='"+tid+"' and t.openTerm='"+openrTerm+"'";

        System.out.println(hql);

        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();

        List<CourseEntity> resultList = new ArrayList<>();
        System.out.println(resultList.size());
        CourseEntity courseEntity=null;
        while(iterator.hasNext()){

            courseEntity = (CourseEntity)iterator.next();
            resultList.add(courseEntity);
        }
        return resultList;
    }
}
