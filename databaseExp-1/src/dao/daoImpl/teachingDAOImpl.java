package dao.daoImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.teachingDAO;
import model.CourseEntity;
import model.TeachingEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.rmi.server.ExportException;
import java.util.*;

public class teachingDAOImpl implements teachingDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public JSONArray findTeachingCourseName(TeachingEntity teachingEntity) {
        String openrTerm = teachingEntity.getOpenTerm();
        String tid = teachingEntity.getTid();

        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "select c.cid,c.cName,c.cCredit,c.cTotalHours,t.scoreRate,t.ttime " +
                "from TeachingEntity t,CourseEntity c " +
                "where t.cid=c.cid and t.tid='"+tid+"' and t.openTerm='"+openrTerm+"'";

        System.out.println(hql);

        Query query = s.createQuery(hql);

        List<JSONObject> resultList = new ArrayList<>();
        List<Object[]> list =query.list();

        for (Object[] object : list) {
            JSONObject json = new JSONObject();
            json.put("cid",object[0]);
            json.put("cName",object[1]);
            json.put("cCredit",object[2]);
            json.put("cTotalHours",object[3]);
            json.put("scoreRate",object[4]);
            json.put("ttime",object[5]);
            resultList.add(json);
        }
        return JSONArray.parseArray(JSON.toJSONString(resultList));
    }

    @Override
    public JSONArray findAllDetail(TeachingEntity teachingEntity) {

        String openTerm = teachingEntity.getOpenTerm();

        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "select teacher.tName, c.cName, teaching.cid, teaching.ttime, teaching.tid from TeachingEntity teaching, TeacherEntity teacher, CourseEntity c"
                + " where teaching .tid = teacher .tid and c .cid = teaching .cid"
                + " and teaching .openTerm = '" + openTerm+"'";

        System.out.println(hql+"");

        Query query = s.createQuery(hql);
        List<Object[]> list =query.list();

        List<JSONObject> resultList = new ArrayList<>();
        for (Object[] object : list) {
            JSONObject json = new JSONObject();
            json.put("cid",object[2]);
            json.put("cName",object[1]);
            json.put("tName",object[0]);
            json.put("ttime",object[3]);
            json.put("tid",object[4]);
            resultList.add(json);
        }

        return JSONArray.parseArray(JSON.toJSONString(resultList));
    }

    @Override
    public JSONArray findAllDetailByQuery(JSONObject jsonObject) {

        Session s = sessionFactory.openSession();
        StringBuffer clause = new StringBuffer(" and ");

        String hql = "select teacher.tName, c.cName, teaching.cid, teaching.ttime, teaching.tid from TeachingEntity teaching, TeacherEntity teacher, CourseEntity c"
                + " where teaching .tid = teacher .tid and c .cid = teaching .cid";

        Boolean flag = false;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            flag = true;
            String key = entry.getKey() ;
            String val = entry.getValue()+"";
            if(!val.equals("")){
                if(key.equals("cid")||key.equals("tid")){
                    clause.append("teaching.");
                }
                clause.append(key+"='"+val+"'&");
            }
        }
        if(flag){
            String where = clause.substring(0,clause.length()-1);
            where = where.replace("&"," and ");
            System.out.println(where);
            hql = hql.concat(where);
            System.out.println(hql);
        }

        Query query = s.createQuery(hql);
        List<Object[]> list =query.list();
        List<JSONObject> resultList = new ArrayList<>();

        for (Object[] object : list) {
            JSONObject json = new JSONObject();
            json.put("tName",object[0]);
            json.put("cName",object[1]);
            json.put("cid",object[2]);


            json.put("ttime",object[3]);
            json.put("tid",object[4]);
            resultList.add(json);
        }
        return JSONArray.parseArray(JSON.toJSONString(resultList));
    }

    @Override
    public Boolean updateScoreRate(TeachingEntity teachingEntity) {
        TeachingEntity tmp = new TeachingEntity();
        tmp.setCid(teachingEntity.getCid());
        tmp.setOpenTerm(teachingEntity.getOpenTerm());
        tmp.setTid(teachingEntity.getTid());

        System.out.println(JSON.toJSONString(tmp));
        BaseDAOImpl<TeachingEntity> baseDAO = new BaseDAOImpl<>();
        try{
            TeachingEntity target = baseDAO.findByQuery(tmp).get(0);
            if(target!=null){
                target.setScoreRate(teachingEntity.getScoreRate());
                baseDAO.update(target);
                System.out.println("updated");
                return true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }
}
