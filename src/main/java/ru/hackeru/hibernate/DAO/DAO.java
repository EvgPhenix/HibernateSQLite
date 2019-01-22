package ru.hackeru.hibernate.DAO;

import java.util.List;

public interface DAO<T> {
    void add(T t);
    List<T> getAll();
    T getById(int id);
    void update(T t);
    void deleteById(int id);
    void deleteById(String className, int id);
}
