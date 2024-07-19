package view;

import business.UserBusiness;
import dto.User;
import view.component.UserComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private final int page;
    private String search;

    public UserManager(int page){
        this.page = page;
    }

    public UserManager(String search, int page){
        this.search = search;
        this.page = page;
    }

    private void makeFunctional(){
        //todo
        usersPanel.setLayout(new BoxLayout(usersPanel,BoxLayout.Y_AXIS));
        UserBusiness userBusiness = new UserBusiness();
        List<User> users;
        if(search != null){
            users = userBusiness.selectAll(Integer.parseInt(searchField.getText()),"=","dni",page);
        }
        else{
            users = userBusiness.selectAll(page);
        }
        for (User u : users){
            usersPanel.add(new UserComponent(u));
        }
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.goTo(new UserManager(searchField.getText(),0));
            }
        });
        goBackButton.addActionListener(new HyperLink<>(new Menu()));

        if(this.page > 0){
            backPage.addActionListener(new HyperLink<>(new UserManager(this.search,page-1)));
        }else{
            backPage.setEnabled(false);
        }
        if(this.page < userBusiness.getTotalPages()){
            nextPage.addActionListener(new HyperLink<>(new UserManager(this.search,page+1)));
        }
        else{
            nextPage.setEnabled(false);
        }


        usersPanel.revalidate();
        usersPanel.repaint();
    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }


}
