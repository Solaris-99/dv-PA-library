package view;

import javax.swing.*;

public class Books implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JPanel content;
    private JButton goBackButton;
    private JScrollPane scroll;

    public JPanel getContent(){

        return content;
    }

}
