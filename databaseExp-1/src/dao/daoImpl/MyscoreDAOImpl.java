package dao.daoImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.MyscoreDAO;
import model.MyscoreEntity;
import model.StudentEntity;
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
    public JSONArray findScoreByCourse(MyscoreEntity myscoreEntity0) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql = "select s.sName,m.sid,m.usualResults,m.examResults,m.totalResults " +
                "from StudentEntity s,MyscoreEntity m  where s.sid=m.sid and m.tid='"+myscoreEntity0.getTid();
        hql+="' and m.cid='"+myscoreEntity0.getCid();
        hql+="' and m.openTerm='"+myscoreEntity0.getOpenTerm()+"'";

        Query query = s.createQuery(hql);
        List<Object[]> list = query.list();
        List<JSONObject> result = new ArrayList<>();
        for (Object[] object : list) {
            JSONObject json = new JSONObject();
            json.put("sName",object[0]);
            json.put("sid",object[1]);
            json.put("usualResults",object[2]);
            json.put("examResults",object[3]);
            json.put("totalResults",object[4]);

            result.add(json);
        }
        return JSONArray.parseArray(JSON.toJSONString(result));
    }

    @Override
    public JSONArray findScoreForStudent(MyscoreEntity myscoreEntity) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String hql = "select c.cid, c.cName,m.usualResults,m.examResults,m.totalResults,m.tid" +
                " from MyscoreEntity m, CourseEntity c where m.cid=c.cid and m.openTerm='"+myscoreEntity.getOpenTerm()+"'"
                +"and m.sid='"+myscoreEntity.getSid()+"'";

        Query query = s.createQuery(hql);
        List<Object[]> list = query.list();
        List<JSONObject> resultList = new ArrayList<>();
        for (Object[] object : list) {
            JSONObject json = new JSONObject();
            json.put("cid",object[0]);
            json.put("cName",object[1]);
            json.put("usualResults",object[2]);
            json.put("examResults",object[3]);
            json.put("totalResults",object[4]);
//            json.put("tid",object[5]);

            resultList.add(json);
        }

        return JSONArray.parseArray(JSON.toJSONString(resultList));

    }


    @Override
    public JSONArray findCourseForStudent(MyscoreEntity myscoreEntity) {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String sid = myscoreEntity.getSid();
        String openTerm = myscoreEntity.getOpenTerm();


        String hql = "select c.cName,c.cid,t.tName,c.cCredit,c.cTotalHours,te.ttime,m.tid" +
                " from MyscoreEntity m, CourseEntity c,TeacherEntity t,TeachingEntity te where m.sid = "+sid+
                " and m.openTerm = '"+openTerm+"' and m.cid = c.cid and t.tid = m.tid " +
                "and te.openTerm = m.openTerm and te.cid = m.cid and te.tid = m.tid";

        Query query = s.createQuery(hql);
        List<Object[]> list = query.list();
        List<JSONObject> resultList = new ArrayList<>();
        for (Object[] object : list) {
            JSONObject json = new JSONObject();

            json.put("cName",object[0]);
            json.put("cid",object[1]);
            json.put("tName",object[2]);
            json.put("cCredit",object[3]);
            json.put("cTotalHours",object[4]);
            json.put("ttime",object[5]);
            json.put("tid",object[6]);

            resultList.add(json);
        }

        return JSONArray.parseArray(JSON.toJSONString(resultList));

    }

    @Override
    public Boolean isDeletable(MyscoreEntity myscoreEntity) {
        try{
            List<MyscoreEntity> list = new BaseDAOImpl<MyscoreEntity>().findByQuery(myscoreEntity);
            MyscoreEntity findit = list.get(0);
            return findit.getExamResults()==null&&findit.getTotalResults()==null&&findit.getUsualResults()==null;
        }catch (Exception ex){
            return false;
        }
    }


}
