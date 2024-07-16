package view;

import helpers.Status;

import javax.swing.*;

public class Menu implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JPanel content;
    private JButton booksButton;
    private JButton lendsButton;
    private JButton userManagerButton;
    private JButton statisticsButton;
    private JButton logoutButton;

    private void makeFunctional(){
        booksButton.addActionListener(new HyperLink<>(new Books()));
        Status status = Status.getInstance();
        if(!status.isEmployee()){
            userManagerButton.setEnabled(false);
            statisticsButton.setEnabled(false);
        }
        else{
            //TODO
            System.out.println("adding employee features..");
        }
        logoutButton.addActionListener(new HyperLink<>(new Login()));


    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }



}
