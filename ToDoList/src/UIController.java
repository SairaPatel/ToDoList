import java.awt.*;

public class UIController {

    final static Color background = new Color(220, 220, 255);
    final static Color midground = new Color(255, 255, 255);
    final static Color foreground = new Color(120, 120, 220);

   
    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2, weightx and weighty = 0.
     * And gridx, gridy, values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.ipadx = 3;
        gbc.ipady = 3;
        gbc.insets = new Insets(2,2,2,2);
        return gbc;
    }

    /**
     * Returns a GridBagConstraints object with: ipadx = 2 ipady = 2. 
     * And gridx, gridy, weightx and weighty values specified by params
     *  
     * @param gridx the column of the component
     * @param gridy the row of the component
     * @param weightx the weight of the component's column
     * @param weighty the weight of the component's row
     * @return the GridBagConstraints object with certain values set
     */
    static public GridBagConstraints getGBC(int gridx, int gridy, int weightx, int weighty){
        GridBagConstraints gbc = getGBC(gridx, gridy);
        gbc.weightx = weightx;
        gbc.weighty = weighty;

        return gbc;
    }

    static public Font getFont(int fontsize){
        return new Font("sans serif", Font.BOLD, fontsize);
    }
}
