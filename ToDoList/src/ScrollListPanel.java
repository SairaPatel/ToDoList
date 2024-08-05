import javax.swing.*;
import java.util.ArrayList;

public class ScrollListPanel extends JScrollPane {

    ScrollListPanel(ArrayList<Task> tasks){
        
        // add task list panel to the scroll pane
        setBorder(BorderFactory.createEmptyBorder());

        ListPanel panel = new ListPanel(tasks); 
        setViewportView(panel);

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
