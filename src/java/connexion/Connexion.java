/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

/**
 *
 * @author Diary
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion{

    public static Connection Connect() throws Exception{
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/construction","postgres","diary");
        return con;
    }

}
