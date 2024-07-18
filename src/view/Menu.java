package view;

import dto.User;
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
    private JButton addBookButton;

    private void makeFunctional(){
        booksButton.addActionListener(new HyperLink<>(new Books("",0)));
        Status status = Status.getInstance();
        if(!status.isEmployee()){
            body.remove(userManagerButton);
            body.remove(statisticsButton);
            body.remove(addBookButton);
        }
        else{
            //TODO
            userManagerButton.addActionListener(new HyperLink<>(new UserManager()));
            statisticsButton.addActionListener(new HyperLink<>(new Statistics()));
            addBookButton.addActionListener(new HyperLink<>(new BookCreator()));
        }
        logoutButton.addActionListener(new HyperLink<>(new Login()));
        lendsButton.addActionListener(new HyperLink<>(new Lends("",0)));

    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }



}
