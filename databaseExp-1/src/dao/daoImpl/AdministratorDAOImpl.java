package dao.daoImpl;

import dao.AdministratorDAO;
import model.AdministratorEntity;
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

//    @Override
//    public void add(AdministratorEntity administratorEntity) {
//        Session s = sessionFactory.openSession();
//        Transaction tx = s.beginTransaction();
//        s.save(administratorEntity);
//        tx.commit();
//    }

    @Override
    public void delete(String id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql="delete AdministratorEntity as p where p.aid=?";

        Query query= s.createQuery(hql);

        query.setParameter(0,id);

        query.executeUpdate();

        tx.commit();
    }

    @Override
    public AdministratorEntity login(String id, String password) {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        //select
        String hql = "from AdministratorEntity as a where a.aid=? and a.aPassword=?";
        Query query = s.createQuery(hql);
        query.setParameter(0,id);
        query.setParameter(1,password);

        List list = query.list();
        Iterator iterator = list.iterator();

        AdministratorEntity administratorEntity=null;
        while(iterator.hasNext()){
            administratorEntity = (AdministratorEntity)iterator.next();
        }
        return administratorEntity;
    }

//    @Override
//    public List<AdministratorEntity> getAll() {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session s = sessionFactory.openSession();
//        Transaction tx = s.beginTransaction();
//        //select
//        String hql = "from AdministratorEntity as a";
//        Query query = s.createQuery(hql);
//
//        List list = query.list();
//        Iterator iterator = list.iterator();
//
//        List<AdministratorEntity> resultList = new ArrayList<>();
//
//        AdministratorEntity administratorEntity=null;
//        while(iterator.hasNext()){
//            administratorEntity = (AdministratorEntity)iterator.next();
//            resultList.add(administratorEntity);
//        }
//        return resultList;
//    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
