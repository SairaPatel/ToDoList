import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.sql.SQLException;

public class CentralPanel extends JFrame{

    JFrame f;
    ScrollListPanel tasksPanel;
    // ListPanel tasksPanel;
    GridBagConstraints tasksGBC; 

    CentralPanel(){

        // set panel layout
        setLayout(new GridBagLayout());
        getContentPane().setBackground(UIController.midColour);
        

        // title
        JLabel title = new JLabel("To Do List");
        title.setFont(UIController.getFont(30));
        title.setForeground(UIController.darkColour);        
        title.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints titleGBC = UIController.getGBC(0, 0);
        titleGBC.fill = GridBagConstraints.HORIZONTAL;
        titleGBC.anchor = GridBagConstraints.CENTER;
        titleGBC.gridwidth = 2;
        add(title, titleGBC);

        // list
        tasksPanel = new ScrollListPanel(DBController.getTasks());
        // tasksPanel = new ListPanel(DBController.getTasks());
        tasksGBC = UIController.getGBC(0,1, 1, 1);
        tasksGBC.fill = GridBagConstraints.BOTH;
        tasksGBC.gridwidth = 2;
        add(tasksPanel, tasksGBC);

        // new task input
        JTextField taskInput = new JTextField();
        GridBagConstraints inputGBC = UIController.getGBC(0,2, 1, 0);
        inputGBC.fill = GridBagConstraints.BOTH;
        add(taskInput, inputGBC);

        // add new task button
        JButton addButton = new JButton("Add Item");
        addButton.setFont(UIController.getFont(12));
        addButton.setBorderPainted(false);
        addButton.setBackground(UIController.darkColour);
        addButton.setForeground(UIController.lightColour);

        GridBagConstraints btnGBC = UIController.getGBC(1,2);
        btnGBC.fill = GridBagConstraints.VERTICAL;
        btnGBC.anchor = GridBagConstraints.LINE_END;
        add(addButton, btnGBC);

        // add task button event
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                        DBController.insertTask(taskInput.getText());
                        taskInput.setText("");
                        reloadTasksPanel();
                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(f, "Could not add task.");
                }
            }
            
        });

        // enter key pressed (add task) event
        taskInput.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        DBController.insertTask(taskInput.getText());
                        taskInput.setText("");
                        reloadTasksPanel();
                    }
                    catch (SQLException ex){
                        JOptionPane.showMessageDialog(f, "Could not add task.");
                    }
                }
            }
            public void keyTyped(KeyEvent e){}
            public void keyPressed(KeyEvent e){}
        });

        // clear button
        JButton clearButton = new JButton("Delete Completed Items");
        clearButton.setFont(UIController.getFont(12));
        clearButton.setBorderPainted(false);
        clearButton.setBackground(UIController.darkColour);
        clearButton.setForeground(UIController.lightColour);

        GridBagConstraints clearGBC = UIController.getGBC(0,3, 1, 0);
        clearGBC.fill = GridBagConstraints.HORIZONTAL;
        clearGBC.gridwidth = 2;
        add(clearButton, clearGBC);


        // delete completed tasks event
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    DBController.deleteTasks();
                    reloadTasksPanel();
                }
                catch (SQLException ex){
                    JOptionPane.showMessageDialog(f, ex.toString());
                }
            }
        });


        setSize(300,500);
        setVisible(true);
        
    }

    private void reloadTasksPanel(){
        remove(tasksPanel);
        tasksPanel = new ScrollListPanel(DBController.getTasks());
        // tasksPanel = new ListPanel(DBController.getTasks());
        add(tasksPanel, tasksGBC);
        revalidate();
    }


}


