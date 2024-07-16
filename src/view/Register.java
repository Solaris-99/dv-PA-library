package view;

import dto.User;

import javax.swing.*;

public class Register implements Viewable {
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField DNIField;
    private JPanel content;

    private void makeFunctional(){
        loginButton.addActionListener(new HyperLink<>(new Login()));
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());//todo: hash pass
        String name = nameField.getText();
        String surname = surnameField.getText();
        int DNI = Integer.parseInt(DNIField.getText());
        User newUser = new User(-1,name,surname,email,password,DNI);
        //register user, etc.
    }


    @Override
    public JPanel getContent(){
        return content;
    }


}
