/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontest;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DAMAR ALI El-Fartouni
 */
public interface DAOInterface<T, DAO> {

    /**
     * Suppression d'une entité en fonction de son identifiant
     *
     * @param entity
     */
    void deleteOneById(T entity) throws SQLException;

    /**
     * Persistance de l'entité Student
     *
     * @param entity
     * @return
     */
    int save(T entity) throws SQLException;
    
    DAO findOneById(int id) throws SQLException;

    DAO findAll() throws SQLException;

    T getOne() throws SQLException;

    Map<String, String> getOneAsMap() throws SQLException;

    List<Student> getAll() throws SQLException;
    
    List<Map<String, String>> getAllAsArray() throws SQLException;
    
}
