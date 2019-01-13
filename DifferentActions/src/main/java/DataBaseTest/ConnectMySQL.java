package DataBaseTest;

import org.testng.annotations.Test;

import java.sql.*;

public class ConnectMySQL {

    @Test
    public void testDB() throws ClassNotFoundException, SQLException {

        // 1. import the package (java.sql.*)
        // 2. load & register the driver (by Class.forname)
        // 3. getConnection
        // 4. createStatement/query
        // 5. executeQuery
        // 6. get the ResultSet & use it
        // 7. close the connection & statement

        Class.forName("com.mysql.jdbc.Driver"); //loading the driver from Driver class located in com.mysql.jdbc package
        System.out.println("Driver loaded");

        //"jdbc:mysql://hostname(where my db is located, if its in my local machine then its 'localhost' & if its in remote/another machine
        // then its ip address of that machine):port/dbname", "username", "password"

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/selenium", "root", "i2love2singing2");
        System.out.println("Database Connected");

        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery("select * from seleniumuser"); //seleniumuser is the table name

        while(rs.next()){
            String firstname = rs.getString("firstname"); //firstname is the column name
            System.out.println("Database record is: " + firstname);

            String email = rs.getString("email"); //email is 2nd column
            System.out.println("Database record is: " + email);
        }

        connection.close();
        st.close();
    }
}
