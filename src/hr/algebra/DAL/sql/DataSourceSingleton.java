/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.DAL.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author frank
 */
public class DataSourceSingleton {
    
    private static final String UID = "franko";
    private static final String DATABASE_NAME = "cinestarDB";
    private static final String SERVER_NAME = "\\SQLEXPRESS";
    private static final String USER_PASSWORD = "123";

   
    private DataSourceSingleton(){
}
    
 private static DataSource instance;
 
 public static DataSource getInstance(){
     if (instance ==null) {
         instance = createInstance();
     }
     return instance;
 }
 
     private static DataSource createInstance() {
      
         SQLServerDataSource dataSource = new SQLServerDataSource();
         dataSource.setServerName(SERVER_NAME);
         dataSource.setDatabaseName(DATABASE_NAME);
         dataSource.setUser(UID);
         dataSource.setPassword(USER_PASSWORD);
         
         return dataSource;
    }
    
}

