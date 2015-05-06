package tubespbogui;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Grinwood
 */
public class database {

    private String dbuser;
    private String dbpassword;
    private java.sql.Statement statement;
    private java.sql.Connection connection;
    private java.sql.ResultSet resultSet;

    public void connect() {
        try {
            setConnection(DriverManager.getConnection("jdbc:mysql://localhost/dbfutsal", "root", ""));
            setStatement(getConnection().createStatement());
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getData(String query) {
        try {
            setResultSet(statement.executeQuery(query));
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getResultSet();
    }

    public void execute(String query) {
        try {
            getStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the statement
     */
    public java.sql.Statement getStatement() {
        return statement;
    }

    /**
     * @return the connection
     */
    public java.sql.Connection getConnection() {
        return connection;
    }

    /**
     * @return the resultSet
     */
    public java.sql.ResultSet getResultSet() {
        return resultSet;
    }

    /**
     * @param statement the statement to set
     */
    public void setStatement(java.sql.Statement statement) {
        this.statement = statement;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(java.sql.Connection connection) {
        this.connection = connection;
    }

    /**
     * @param resultSet the resultSet to set
     */
    public void setResultSet(java.sql.ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
