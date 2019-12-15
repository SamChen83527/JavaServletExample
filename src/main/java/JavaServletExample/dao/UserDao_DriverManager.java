/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServletExample.dao;

import JavaServletExample.bean.UserBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sam
 */
public class UserDao_DriverManager implements Dao<UserBean> {
    private static final UserDao_DriverManager instance = new UserDao_DriverManager();
    
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/backend?serverTimezone=UTC";
    
    //  Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public UserDao_DriverManager(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDao_DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Optional<UserBean> get(long id) {
        UserBean users = new UserBean();
        return Optional.ofNullable(users);
    }
     
    @Override
    public List<UserBean> getAll() {
        ArrayList<UserBean> users = new ArrayList<>();
        return users;
    }  
    
    @Override
    public void insert(UserBean user) {
        try (Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO registration (account, password) VALUE (?,?)");) {
            
            //Statement statement = conn.createStatement();
            //statement.executeUpdate("insert into registration values(default,'" + user.getUsername() + "','" + user.getPassword() + "');");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao_DriverManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("A post error occoured. " + ex.getMessage());
        }
    }
     
    @Override
    public void update(UserBean user, String[] params) {
        user.setUsername(Objects.requireNonNull(
            params[0], "Name cannot be null")
        );
        user.setPassword(Objects.requireNonNull(
            params[1], "Password cannot be null")
        );
    }
     
    @Override
    public void delete(UserBean user) {
        ArrayList<UserBean> users = new ArrayList<>();
        users.remove(user);
    }
    
    public static UserDao_DriverManager getInstance(){
        return instance;
    }
    
    private Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL,USER,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao_DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
