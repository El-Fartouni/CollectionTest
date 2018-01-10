/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectiontest;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DAMAR ALI El-Fartouni
 */
public class StudentDAO implements DAOInterface<Student, StudentDAO> {

    private Connection dbConnection;

    private PreparedStatement pStatement;

    private ResultSet resultSet;

    public StudentDAO(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Persistance de l'entité Student
     *
     * @param entity
     * @return
     */
    @Override
    public int save(Student entity) throws SQLException {
        int affectedRows;
        if (entity.getId() == null) {
            //insertion
            affectedRows = this.insert(entity);
        } else {
            //mise à jour
            affectedRows = this.update(entity);
        }

        return affectedRows;
    }

    /**
     * Insertion dans la base de données
     *
     * @param entity
     * @return
     */
    private int insert(Student entity) throws SQLException {
        String sql = "INSERT INTO students (name, first_name, sex) VALUES (?,?,?)";
        pStatement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        pStatement.setString(1, entity.getName());
        pStatement.setString(2, entity.getFirstName());
        pStatement.setString(3, String.valueOf(entity.getSex()));

        //Récupération de la clef incrémentée
        ResultSet keyRs = pStatement.getGeneratedKeys();
        if (keyRs.next()) {
            entity.setId(keyRs.getInt("id"));
        }

        return pStatement.executeUpdate();
    }

    /**
     * Insertion dans la base de données
     *
     * @param entity
     * @return
     */
    private int update(Student entity) throws SQLException {
        String sql = "UPDATE students SET name=?, first_name=?, sex=? WHERE id=?";
        pStatement = dbConnection.prepareStatement(sql);

        pStatement.setString(1, entity.getName());
        pStatement.setString(2, entity.getFirstName());
        pStatement.setString(3, String.valueOf(entity.getSex()));
        pStatement.setInt(4, entity.getId());

        return pStatement.executeUpdate();
    }

    /**
     * Suppression d'une entité en fonction de son identifiant
     *
     * @param entity
     */
    @Override
    public void deleteOneById(Student entity) throws SQLException {
        if (entity.getId() != null) {
            String sql = "DELETE FROM students WHERE id=?";
            pStatement = dbConnection.prepareStatement(sql);
            pStatement.setInt(1, entity.getId());
            pStatement.executeUpdate();
        }
    }

    /**
     * Récupération d'une entitité en fonction de sa clef primaire
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public StudentDAO findOneById(int id) throws SQLException {

        String sql = "SELECT * FROM students WHERE id=?";
        pStatement = dbConnection.prepareStatement(sql);
        pStatement.setInt(1, id);
        resultSet = pStatement.executeQuery();

        return this;
    }

    /**
     * Exécute une requête sélectionnant l'ensemble des lignes de la table
     *
     * @return
     * @throws SQLException
     */
    public StudentDAO findAll() throws SQLException {

        String sql = "SELECT * FROM students";
        pStatement = dbConnection.prepareStatement(sql);
        resultSet = pStatement.executeQuery();

        return this;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'une entité
     *
     * @return
     * @throws SQLException
     */
    public Student getOne() throws SQLException {
        Student entity = new Student();
        if (resultSet.next()) {
            entity.setName(resultSet.getString("name"));
            entity.setFirstName(resultSet.getString("first_name"));
            entity.setSex(resultSet.getString("sex").charAt(0));
        }
        return entity;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'un tableau
     * associatif
     *
     * @return
     * @throws SQLException
     */
    public Map<String, String> getOneAsMap() throws SQLException {
        Map<String, String> studentData = new HashMap<>();

        if (resultSet.next()) {
            studentData.put("name", resultSet.getString("name"));
            studentData.put("firstName", resultSet.getString("firstName"));
            studentData.put("sex", resultSet.getString("sex"));
        }
        return studentData;
    }

    /**
     * Récupération des résultats d'une requête sous la forme d'une liste
     * d'entités
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List<Student> getAll() throws SQLException {
        List<Student> studentList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                studentList.add(this.getOne());
            }
        }

        return studentList;
    }

    public List<Map<String, String>> getAllAsArray() throws SQLException {
        List<Map<String, String>> studentList = new ArrayList<>();

        if (resultSet.isBeforeFirst()) {
            while (!resultSet.isLast()) {
                studentList.add(this.getOneAsMap());
            }
        }

        return studentList;
    }

}
