package view;

import javax.swing.*;

public class UserManager implements Viewable {
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JButton goBackButton;
    private JPanel usersPanel;
    private JButton backPage;
    private JButton nextPage;
    private JPanel searchPanel;
    private JTextField searchField;
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
