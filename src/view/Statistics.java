package view;

import javax.swing.*;

public class Statistics implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JButton goBackButton;
    private JLabel statisticsLabel;
    private JPanel content;

    public void makeFunctional(){
        //TODO
        goBackButton.addActionListener(new HyperLink<>(new Menu()));
    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }

}
