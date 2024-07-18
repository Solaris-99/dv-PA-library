package view.component;

import dto.User;

import javax.swing.*;

public class UserComponent extends JPanel {

    private final JButton deleteUser;

    public UserComponent(User user){
        this.add(new JLabel(user.getFullName()));
        this.add(new JLabel(user.email()));
        this.add(new JLabel(""+user.DNI()));
        this.deleteUser = new JButton("Eliminar");
    }



}
