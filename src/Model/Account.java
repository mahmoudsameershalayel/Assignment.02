    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP-DALAL
 */
public class Account {

    private int id;
    private int user_id;
    private int account_number;
    private String username;
    private String currency;
    private double balance;
    private String creation_date;

    public Account(int user_id, int account_number, String username, String currency, double balance) {
        this.user_id = user_id;
        this.account_number = account_number;
        this.username = username;
        this.currency = currency;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public int insert() throws ClassNotFoundException, SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String query = "INSERT INTO accounts(id,user_id,account_number,username,currency,balance) VALUES(?,?,?,?,?,?)";
        ps = c.prepareStatement(query);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getUser_id());
        ps.setInt(3, this.getAccount_number());
        ps.setString(4, this.getUsername());
        ps.setString(5, this.getCurrency());
        ps.setDouble(6, this.getBalance());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " inserted successfully");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int update() throws ClassNotFoundException, SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String query = ("UPDATE accounts SET user_id=? , account_number=? , username=? , currency=? , creation_date=? WHERE id=?");
        ps = c.prepareStatement(query);
        ps.setInt(1, this.getUser_id());
        ps.setInt(2, this.getAccount_number());
        ps.setString(3, this.getUsername());
        ps.setString(4, this.getCurrency());
        ps.setString(5, this.getCreation_date());
        ps.setInt(6, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : " + this.getId() + " updated successfully.");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public static ArrayList<Account> getAllAccounts() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts ";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        int userId = rs.getInt(2);
        int accountNumber = rs.getInt(3);
        String username = rs.getString(4);
        String currency = rs.getString(5);
        double balance = rs.getDouble(6);
        
        while (rs.next()) {
            Account account = new Account(userId, accountNumber, username, currency, balance);
            account.setId(rs.getInt(1));
            accounts.add(account);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return accounts;
    }

    public int delete() throws ClassNotFoundException, SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String query = "DELETE FROM accounts WHERE id=?";
        ps = c.prepareStatement(query);
        ps.setInt(1, this.id);
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The account with id : " + this.getId() + " deleted successfully.");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

}
