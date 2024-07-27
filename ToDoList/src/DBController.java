
import java.sql.*;
import java.util.ArrayList;

public class DBController {

    static final String DB_URL =  "jdbc:mysql://localhost/ToDoList";
    static final String USER = "user";
    static final String PASS = "user";


    static public ArrayList<Task> getTasks() throws SQLException{
        String qry = "SELECT description, done FROM Tasks";
        ArrayList<Task> tasks = new ArrayList<Task>();

        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet results = st.executeQuery(qry);
        )
        {
            while (results.next()){
                String task = results.getString("description");
                int done = results.getInt("done");

                tasks.add(new Task(task, done == 1));
            }
        }
        catch ( SQLException e){
            throw e;
        }

        return tasks;
    }

}
