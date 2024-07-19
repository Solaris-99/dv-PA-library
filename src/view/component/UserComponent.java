package view.component;

import business.UserBusiness;
import dto.User;
import view.UserManager;
import view.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserComponent extends JPanel {

    private final JButton deleteUser;

    public UserComponent(User user){
        this.add(new JLabel(user.getFullName()));
        this.add(new JLabel(user.email()));
        this.add(new JLabel(""+user.DNI()));
        this.deleteUser = new JButton("Eliminar");
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserBusiness userBusiness = new UserBusiness();
                userBusiness.delete(user.id());
                Window.goTo(new UserManager());
            }
        });
    }



}
