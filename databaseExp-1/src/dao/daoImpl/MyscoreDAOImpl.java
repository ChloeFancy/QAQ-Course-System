package dao.daoImpl;

import dao.MyscoreDAO;
import model.MyscoreEntity;
import model.ScoreEntity;
import model.TeachingEntity;
import model.microModel.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyscoreDAOImpl implements MyscoreDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<MyscoreEntity> findScoreByCourse(MyscoreEntity myscoreEntity0) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql = "from MyscoreEntity as score where score.tid='"+myscoreEntity0.getTid();
        hql+="' and score.cid='"+myscoreEntity0.getCid();
        hql+="' and score.openTerm='"+myscoreEntity0.getOpenTerm()+"'";

        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();
        List<MyscoreEntity> result = new ArrayList<>();
        while(iterator.hasNext()){
            MyscoreEntity myscoreEntity = (MyscoreEntity) iterator.next();
            MyscoreEntity myscoreEntity_1 = new MyscoreEntity();

            myscoreEntity_1.setSid(myscoreEntity.getSid());
            myscoreEntity_1.setUsualResults(myscoreEntity.getUsualResults());
            myscoreEntity_1.setExamResults(myscoreEntity.getExamResults());
            myscoreEntity_1.setTotalResults(myscoreEntity.getTotalResults());

            result.add(myscoreEntity_1);
        }
        return result;
    }
}
