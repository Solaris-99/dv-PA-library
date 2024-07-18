package view;

import javax.swing.*;

public class BookCreator implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JButton goBackButton;
    private JLabel messageLabel;
    private JPanel bookPanel;
    private JComboBox authorSelection;
    private JComboBox publisherSelection;
    private JButton addABookButton;
    private JButton addAuthorButton;
    private JTextField titleField;
    private JTextField yearField;
    private JTextField copiesField;
    private JTextField authorField;
    private JTextField publisherField;
    private JButton addPublisherButton;
    private JPanel content;

    private void makeFunctional(){
        //todo
        goBackButton.addActionListener(new HyperLink<>(new Menu()));
    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }

}
