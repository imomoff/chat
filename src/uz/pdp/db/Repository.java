package uz.pdp.db;

import java.util.List;

public interface Repository<T> {
    void save(T t);
    List<T> findAll();
    void update(T t, Integer id);
    void delete(T t);
}
