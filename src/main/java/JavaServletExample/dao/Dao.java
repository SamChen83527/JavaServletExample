/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServletExample.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sam
 * @param <T>
 */
public interface Dao<T> {
    // READ
    Optional<T> get(long id);
    
    List<T> getAll();
     
    // CREATE
    void insert(T t);
    
    // UPDATE
    void update(T t, String[] params);
    
    // DELETE
    void delete(T t);
}