import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListPanel extends JPanel{

    JPanel f;
    private GridBagLayout layout;
    private ArrayList<JCheckBox> checkboxes; 

    private GridBagConstraints gbc;

    ListPanel(ArrayList<Task> tasks){

       setBackground(UIController.background);

        // set panel layout
        layout = new GridBagLayout();
        setLayout(layout);

        // set gbc for checkboxes
        gbc = UIController.getGBC(0,0, 1, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;

        // add tasks
        checkboxes = new ArrayList<JCheckBox>();
        for (Task t: tasks){
            addItem(t);
        }

    }

    public void addItem(Task t){
        // new button
        JCheckBox item = new JCheckBox(t.description, t.done);
        item.setFont(UIController.getFont(12));
        item.setBackground(UIController.midground);
        item.setForeground(UIController.foreground);

        // index num 
        int i = checkboxes.size();

        // reset previous checkbox's row weight to 0
        if (i != 0){
            gbc.weighty = 0;
            layout.setConstraints(checkboxes.get(i -1), gbc);
        }

        // update newly added checkbox
        gbc.gridy = i;
        gbc.weighty = 1;
        checkboxes.add(item);
        add(item, gbc);

        revalidate();

    }

}
