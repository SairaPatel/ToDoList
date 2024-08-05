
import java.sql.*;
import java.util.ArrayList;

public class DBController {

    static final String DB_URL =  "jdbc:mysql://localhost/ToDoList";
    static final String USER = "user";
    static final String PASS = "user";


    // Get array list of all tasks as Task objects
    static public ArrayList<Task> getTasks(){
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
            tasks.add(new Task("Could not load tasks", false));
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
            st.close();
            conn.close();
        }
        catch (SQLException e){
            throw e;
        }
    }

    // delete all completed tasks
    static public void deleteTasks() throws SQLException{
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st = conn.createStatement();
        )
        {
            // update task
            st.executeUpdate("DELETE FROM Tasks WHERE done = 1;");
            st.close();
            conn.close();
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

            st.close();
            conn.close();

            return id;

        }
        catch (SQLException e){
            throw e;
        }

    }





}
