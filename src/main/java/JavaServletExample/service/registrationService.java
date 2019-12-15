/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServletExample.service;

import JavaServletExample.bean.UserBean;
import JavaServletExample.dao.Dao;
import JavaServletExample.dao.UserDao;

/**
 *
 * @author Sam
 */
public class registrationService {
    UserBean user;
    Dao userdao;
    public registrationService (String firstname, String lastname, String email, String username, String password){
        this.user = new UserBean(firstname, lastname, email, username, password);
    }
    
    public void registerUser () {
        userdao = new UserDao();
        userdao.insert(user);
    }
}