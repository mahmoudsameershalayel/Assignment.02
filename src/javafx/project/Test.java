/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx.project;

import Model.Account;
import Model.Accounts;
import Model.AccountsJpaController;
import Model.Users;
import Model.UsersJpaController;
import static javafx.application.Application.launch;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HP-DALAL
 */
public class Test {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
         AccountsJpaController ajc = new AccountsJpaController(emf);
         Accounts a = new Accounts(null , 2,3,"test","test",80);
         ajc.create(a);
        
    }
}
