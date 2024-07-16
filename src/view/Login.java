package view;

import business.AuthBusiness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements Viewable {
    private JButton loginButton;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JLabel titleLabel;
    private JPanel content;
    private JButton registerButton;
    private JPanel title;
    private JPanel body;
    private JLabel messageLabel;

    private void makeFunctional(){


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthBusiness authBusiness = new AuthBusiness();
                boolean login = authBusiness.login(emailField.getText(),new String(passwordField.getPassword()));
                if(login){
                    Window.goTo(new Menu());
                }
                else{
                    messageLabel.setText("Email/Contraseña incorrecta(s)");
                }
            }
        });
    }


    @Override
    public JPanel getContent(){
        makeFunctional();
        return this.content;
    }



}
