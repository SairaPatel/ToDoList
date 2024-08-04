import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

public class CentralPanel extends JFrame{

    JFrame f;

    CentralPanel(){

        // set panel layout
        setLayout(new GridBagLayout());
        getContentPane().setBackground(UIController.background);
        

        // title
        JLabel title = new JLabel("To Do List");
        title.setFont(UIController.getFont(30));
        title.setForeground(UIController.foreground);        
        title.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints titleGBC = UIController.getGBC(0, 0);
        titleGBC.fill = GridBagConstraints.HORIZONTAL;
        titleGBC.anchor = GridBagConstraints.CENTER;
        titleGBC.gridwidth = 2;
        add(title, titleGBC);

        // list
        ListPanel tasksPanel = new ListPanel(DBController.getTasks());
        GridBagConstraints listGBC = UIController.getGBC(0,1, 1, 1);
        listGBC.fill = GridBagConstraints.BOTH;
        listGBC.gridwidth = 2;
        add(tasksPanel, listGBC);

        // new task input
        JTextField taskInput = new JTextField();
        GridBagConstraints inputGBC = UIController.getGBC(0,2, 1, 0);
        inputGBC.fill = GridBagConstraints.BOTH;
        add(taskInput, inputGBC);

        // new button
        JButton addButton = new JButton("Add Item");
        addButton.setFont(UIController.getFont(12));
        addButton.setBorderPainted(false);
        addButton.setBackground(UIController.foreground);
        addButton.setForeground(UIController.midground);

        GridBagConstraints btnGBC = UIController.getGBC(1,2);
        btnGBC.fill = GridBagConstraints.VERTICAL;
        btnGBC.anchor = GridBagConstraints.LINE_END;
        add(addButton, btnGBC);

        // clear button
        JButton clearButton = new JButton("Delete Completed Items");
        clearButton.setFont(UIController.getFont(12));
        clearButton.setBorderPainted(false);
        clearButton.setBackground(UIController.foreground);
        clearButton.setForeground(UIController.midground);

        GridBagConstraints clearGBC = UIController.getGBC(0,3, 1, 0);
        clearGBC.fill = GridBagConstraints.HORIZONTAL;
        clearGBC.gridwidth = 2;
        add(clearButton, clearGBC);

        setSize(300,500);
        setVisible(true);
        
    }

}
