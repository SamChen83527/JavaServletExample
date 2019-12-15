/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServletExample.dao;

import JavaServletExample.bean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Sam
 */
public class UserDao implements Dao<UserBean>{
    
    public UserDao(){
        // initiate database
        try (   Connection conn = getConnection();
                //PreparedStatement statement1 = conn.prepareStatement("DROP TABLE IF EXISTS registration;");
                PreparedStatement statement = conn.prepareStatement("CREATE TABLE IF NOT EXISTS registration(rid SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, firstname VARCHAR(50), lastname VARCHAR(50), email VARCHAR(50), username VARCHAR(50), password VARCHAR(50), primary key(rid));");) {            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("A post error occoured. " + ex.getMessage());
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
            PreparedStatement statement = conn.prepareStatement("INSERT INTO registration (firstname, lastname, email, username, password) VALUE (?,?,?,?,?)");) {
            
            //Statement statement = conn.createStatement();
            //statement.executeUpdate("insert into registration values(default,'" + user.getUsername() + "','" + user.getPassword() + "');");
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
         
//        users.add(user);
    }
     
    @Override
    public void delete(UserBean user) {
        ArrayList<UserBean> users = new ArrayList<>();
        users.remove(user);
    }
    
    private static Connection getConnection (){
        DataSource ds = null;
        Connection conn = null;
        try {
            // Get DataSource
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI Context could not found");
            } else {
                ds = (DataSource)ctx.lookup("java:comp/env/jdbc/backend_database");
                if (ds == null) {
                    throw new RuntimeException("DataSource could not found");
                } else {
                    // Get Connection and Statement
                    conn = ds.getConnection();  
                }
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("A JNDI error occoured. " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("A database error occoured. " + ex.getMessage());
        }
        return conn;  
    }
}