import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListPanel extends JPanel{

    JPanel f;

    ListPanel(ArrayList<Task> tasks){

       setBackground(UIController.background);

        // set panel layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        // set gbc for checkboxes
        GridBagConstraints gbc = UIController.getGBC(0,0, 1, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;


        for (int i = 0; i < tasks.size(); i++){

            // create checkbox
            JCheckBox item = new JCheckBox(tasks.get(i).description, tasks.get(i).done);
            item.setFont(UIController.getFont(12));
            item.setBackground(UIController.midground);
            item.setForeground(UIController.foreground);

            // position row
            gbc.gridy = i;

            // set row weighting of last task to 1
            if (i == tasks.size() - 1){
                gbc.weighty = 1;
            }

            // add action listener for checking box
            int j = i;
            item.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e){
                    try{
                        DBController.updateTask(j, e.getStateChange()==1);
                    }
                    catch(SQLException ex){
                        JOptionPane.showMessageDialog(f, "Could not update/check task.");
                    }
                }
            });

            add(item, gbc);
        }

    }

    
    

}
