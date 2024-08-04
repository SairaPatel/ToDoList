import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListPanel extends JPanel{

    JPanel f;

    ListPanel(ArrayList<Task> tasks){

       setBackground(UIController.background);

        // set panel layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = UIController.getGBC(0,0, 1, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;

        // add tasks
        for (int i = 0; i < tasks.size(); i ++){

            // new button
            JCheckBox item = new JCheckBox(tasks.get(i).description, tasks.get(i).done);
            item.setFont(UIController.getFont(12));
            item.setBackground(UIController.midground);
            item.setForeground(UIController.foreground);
            gbc.gridy = i;

            if (i == tasks.size() - 1){
                gbc.weighty = 1;
            }
            add(item, gbc);
        }

        
    }

}
