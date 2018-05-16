package dao.daoImpl;

import dao.ScoreDAO;
import model.ScoreEntity;
import model.microModel.Course;
import model.microModel.Score;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAOImpl implements ScoreDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public List<Object> findSomeCourse(ScoreEntity scoreEntity) {
        List<Object> result = new ArrayList<>();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql = "from ScoreEntity as score where score.sid="+scoreEntity.getSid();
//        hql+=" and score.cid="+scoreEntity.getCid();
        hql+=" and score.semester='"+scoreEntity.getSemester()+"'";
        hql+=" and score.academicYear='"+scoreEntity.getAcademicYear()+"'";

        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();
        ScoreEntity scoreEntity1 = null;
        while(iterator.hasNext()){
            scoreEntity1 = (ScoreEntity) iterator.next();
            Course course = new Course();
            course.setAcademicYear(scoreEntity1.getAcademicYear());
            course.setSemester(scoreEntity1.getSemester());
            course.setCid(scoreEntity1.getCid());
            result.add(course);
        }
        return result;
    }

    //查找当前学年学期的成绩，
    //或者根据前端的成绩查询
    @Override
    public List<Object> findSomeScore(ScoreEntity scoreEntity) {
        List<Object> result = new ArrayList<>();
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql = "from ScoreEntity as score where score.sid="+scoreEntity.getSid();
//        hql+=" and score.cid="+scoreEntity.getCid();
        hql+=" and score.semester='"+scoreEntity.getSemester()+"'";
        hql+=" and score.academicYear='"+scoreEntity.getAcademicYear()+"'";
System.out.println(hql);
        Query query = s.createQuery(hql);
        List list = query.list();
        Iterator iterator = list.iterator();
        ScoreEntity scoreEntity1 = null;
        while(iterator.hasNext()){
            scoreEntity1 = (ScoreEntity) iterator.next();
            if(scoreEntity!=null){
                Score score = new Score();
                score.setAcademicYear(scoreEntity1.getAcademicYear());
                score.setSemester(scoreEntity1.getSemester());
                score.setCid(scoreEntity1.getCid());
                try{
                    score.setUsualResults(scoreEntity1.getUsualResults());
                    score.setExamResults(scoreEntity1.getExamResults());
                    score.setTotalResults(scoreEntity1.getTotalResults());
                }catch (Exception ex){
                    if(score==null) System.out.println(1);
                    if(scoreEntity1==null) System.out.println(2);
                    System.out.println("???");
                }
                result.add(score);
            }
        }
        return result;
    }


}
