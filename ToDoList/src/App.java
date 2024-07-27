public class App {
    public static void main(String[] args) throws Exception {

        System.out.println(DBController.getTasks().size());
        for (Task t: DBController.getTasks()){
            System.out.println(String.format("%s %b", t.description, t.done));
        }

        // DBController.deleteTask(3);

        System.out.println(DBController.getTasks().size());
        for (Task t: DBController.getTasks()){
            System.out.println(String.format("%s %b", t.description, t.done));
        }
    }
}
