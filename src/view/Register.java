package view;

import business.AuthBusiness;
import business.UserBusiness;
import dto.User;
import helpers.Status;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String rawPass = new String(passwordField.getPassword());
                String password = BCrypt.hashpw(rawPass,BCrypt.gensalt());
                String name = nameField.getText();
                String surname = surnameField.getText();
                int DNI = Integer.parseInt(DNIField.getText());
                User newUser = new User(-1,name,surname,email,password,DNI);
                UserBusiness userBusiness = new UserBusiness();
                userBusiness.create(newUser);
                AuthBusiness authBusiness = new AuthBusiness();
                if(authBusiness.login(email,rawPass)){
                    Window.goTo(new Menu());
                }
            }
        });


    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }


}
