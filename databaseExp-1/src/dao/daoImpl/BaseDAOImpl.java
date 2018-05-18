package dao.daoImpl;

import dao.BaseDAO;
import model.AdministratorEntity;
import model.TeacherEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import sun.reflect.annotation.ExceptionProxy;
import util.MD5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BaseDAOImpl<T> implements BaseDAO<T> {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public String insert(T t) throws Exception {
        try{
            Session s = sessionFactory.openSession();
            Transaction tx = s.beginTransaction();
            s.save(t);
            tx.commit();
            return "success";
        }catch (Exception ex){
            ex.printStackTrace();
            return "fail";
        }
    }

    @Override
    public T findOne(T t) throws Exception {
        //实现了login功能，因为判断实例是否相等时只判断了id、pwd
        try{
            Session s = sessionFactory.openSession();
            Transaction tx = s.beginTransaction();
            String className = t.getClass().getName();
            className = className.substring(className.indexOf(".")+1);

            String hql = "from "+className+" as a";
            Query query = s.createQuery(hql);

            List<T> list = query.list();

            for(T tmp : list){
                if(tmp.equals(t)){
                    tx.commit();
                    return tmp;
                }
            }
            tx.commit();
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public List<T> findByQuery(T t) throws Exception {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String className = t.getClass().getName();
        className = className.substring(className.indexOf(".")+1);
        Field[] field = t.getClass().getDeclaredFields();

        StringBuffer hql = new StringBuffer("from "+className+" as a ");

        for(int j=0 ; j<field.length; j++) { //遍历所有属性
            String name = field[j].getName(); //获取属性的名字
            String get = "get";
            if(name.charAt(1)>='a'&&name.charAt(1)<='z'){
                get+=name.substring(0,1).toUpperCase()+name.substring(1);
            }else{
                get+=name;
            }
            Method m = t.getClass().getMethod(get);
            String value = ""+ m.invoke(t);
            if(!value.equals("null")){
                if(hql.indexOf("&")==-1){
                    hql.append("where ");
                }
                hql.append("a."+name+"='"+value + "'&");
            }
        }
        System.out.println(hql.substring(0,hql.length()-1).replace("&"," and "));
        Query query = s.createQuery(hql.substring(0,hql.length()-1).replace("&"," and "));

        List list = query.list();
        Iterator iterator = list.iterator();

        List<T> resultList = new ArrayList<>();
        T administratorEntity=null;
        while(iterator.hasNext()){
            administratorEntity = (T)iterator.next();
            resultList.add(administratorEntity);
        }
        return resultList;
    }

    @Override
    public List<T> findList(T t) throws Exception {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
        String className = t.getClass().getName();
        className = className.substring(className.indexOf(".")+1);
        String hql = "from "+className+" as a";
        Query query = s.createQuery(hql);

        List list = query.list();
        Iterator iterator = list.iterator();

        List<T> resultList = new ArrayList<>();

        T administratorEntity=null;
        String getpwd = "get"+className.substring(0,1).toLowerCase()+"Password";
        String setPwd = getpwd.replace("get","set");

        System.out.println(getpwd+"  "+setPwd);
        while(iterator.hasNext()){
            administratorEntity = (T)iterator.next();
//            Method m = administratorEntity.getClass().getMethod(getpwd);
////            String value = ""+ m.invoke(t);
////            String decode = MD5.hexStringToBytes(value);
////            System.out.println(value+" "+decode);
////            Method setMethod = administratorEntity.getClass().getMethod(setPwd,String.class);
////            setMethod.invoke(t,decode);

            resultList.add(administratorEntity);
        }
        return resultList;
    }

    @Override
    public List<T> findPage(T t, int pageSize, int pageNum) throws Exception {
        return null;
    }

    @Override
    public long countAll(T t) throws Exception {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        String className = t.getClass().getName();
        className = className.substring(className.indexOf(".")+1);
        String hql = "select count(*) from " +className+
                " as a";
        Query query = s.createQuery(hql);

        return ((Long)query.uniqueResult()).intValue();
    }

    @Override
    public int update(T t) throws Exception {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();
//        String className = t.getClass().getName();
//        className = className.substring(className.indexOf(".")+1);
//        Field[] field = t.getClass().getDeclaredFields();
//        for(int j=0 ; j<field.length; j++) { //遍历所有属性
//            String name = field[j].getName(); //获取属性的名字
//            String get = "get";
//            if(name.charAt(1)>='a'&&name.charAt(1)<='z'){
//                get+=name.substring(0,1).toUpperCase()+name.substring(1);
//            }else{
//                get+=name;
//            }
//            Method m = t.getClass().getMethod(get);
//            String value = (String) m.invoke(t);
//            System.out.println("attribute name: " + name+", value: "+value);
//        }
        s.update(t);
        tx.commit();
        return 0;
    }

    @Override
    public int delete(T t) throws Exception {
        Session s = sessionFactory.openSession();
        Transaction tx = s.beginTransaction();

        s.delete(t);
        tx.commit();

        return 0;
    }


//    @Override
//    public int deleteById(String id,String ident) throws Exception {
//        Session s = sessionFactory.openSession();
//        Transaction tx = s.beginTransaction();
////ident=Student/Teacher/Administrator
//        String hql = "delete from " +ident.concat("Entity")+
//                " as a where a."+ident.substring(0,1).toLowerCase()+"id="+id;
//        System.out.print(hql);
//        Query query = s.createQuery(hql);
//
//        query.executeUpdate();
//        tx.commit();
//
//        return 0;
//    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
