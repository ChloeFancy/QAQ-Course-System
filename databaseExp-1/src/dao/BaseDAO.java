package dao;

import java.util.List;

public interface BaseDAO<T> {
    String insert(T t) throws Exception;

    T findOne(T t) throws Exception;

    List<T> findByQuery(T t) throws Exception;

    List<T> findList(T t) throws Exception;

    List<T> findPage(T t, int pageSize, int pageNum) throws Exception;

    long countAll(T t) throws Exception;

    int update(T t) throws Exception;

    int delete(T t) throws Exception;

}
