/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.spring.repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mirek
 */
public interface Repository<T> {
    T get(Serializable id);
    List<T> getAll();
    T create(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}
