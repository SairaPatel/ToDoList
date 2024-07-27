
import java.sql.*;
import java.util.ArrayList;

public class DBController {

    static final String DB_URL =  "jdbc:mysql://localhost/ToDoList";
    static final String USER = "user";
    static final String PASS = "user";


    // Get array list of all tasks as Task objects
    static public ArrayList<Task> getTasks() throws SQLException{
        String qry = "SELECT description, done FROM Tasks;";
        ArrayList<Task> tasks = new ArrayList<Task>();

        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);
        )
        {
            while (rs.next()){
                String task = rs.getString("description");
                int done = rs.getInt("done");

                tasks.add(new Task(task, done == 1));
            }

            rs.close();
            st.close();
            conn.close();
        }
        catch ( SQLException e){
            throw e;
        }

        return tasks;
    }

    // insert a task
    static public void insertTask(String task) throws SQLException{
        try(
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement("INSERT INTO Tasks (description, done) VALUES (?, 0);");
        )
        {
            st.setString(1, task);
            st.executeUpdate();

            st.close();
            conn.close();

        }
        catch (SQLException e){
            throw e;
        }

    }


    // update a task's done property (set done to true or false) 
    static public void updateTask(int taskIndex, boolean done) throws SQLException{
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement("UPDATE Tasks SET done = ? WHERE id = ? ;");
        )
        {
            // update task
            st.setInt(1, done? 1 : 0 );
            st.setInt(2, getTaskIDFromIndex(taskIndex));
            st.executeUpdate();
        }
        catch (SQLException e){
            throw e;
        }
    }

    // delete a task
    static public void deleteTask(int taskIndex) throws SQLException{
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement st = conn.prepareStatement("DELETE FROM Tasks WHERE id = ? ;");
        )
        {
            // update task
            st.setInt(1, getTaskIDFromIndex(taskIndex));
            st.executeUpdate();
        }
        catch (SQLException e){
            throw e;
        }
    }

    // get the id of a task from its index in the list of tasks
    static private int getTaskIDFromIndex(int taskIndex) throws SQLException{
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM Tasks;");
            )
        {
            int id = 0;
            for (int i = -1; i < taskIndex; ++i){
                rs.next();
                id = rs.getInt("id");
            }

            return id;

        }
        catch (SQLException e){
            throw e;
        }

    }





}
