package view;

import business.LendBusiness;
import dto.Lend;
import helpers.Status;
import view.component.LendComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Lends implements Viewable {
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JButton goBackButton;
    private JPanel content;
    private JButton backPage;
    private JButton nextPage;
    private JPanel lendsPanel;
    private JTextField searchField;
    private JPanel searchPanel;
    private final int page;
    private final String search;

    public Lends(String search, int page){
        this.page = page;
        this.search = search;
    }

    private void makeFunctional(){
        lendsPanel.setLayout(new BoxLayout(lendsPanel,BoxLayout.Y_AXIS));
        LendBusiness lendBusiness = new LendBusiness();
        Status status = Status.getInstance();
        List<Lend> lends;
        //employee functions
        if(status.isEmployee()){
            searchField.setText(this.search);
            searchField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Window.goTo(new Lends(searchField.getText(),page));
                }
            });
            if(this.search.isBlank()){
                lends = lendBusiness.selectAll(this.page);
            }else{
                lends = lendBusiness.selectAll(this.search, this.page);
            }
        }
        else{
            body.remove(searchPanel);
            lends = lendBusiness.selectAll(status.getUserId(),"=","id_user",this.page);
        }

        for(Lend lend : lends){
            lendsPanel.add(new LendComponent(lend));
        }

        lendsPanel.revalidate();
        lendsPanel.repaint();
        //pagination
        if(this.page > 0){
            backPage.addActionListener(new HyperLink<>(new Lends(this.search,page-1)));
        }else{
            backPage.setEnabled(false);
        }
        if(this.page < lendBusiness.getTotalPages()){
            nextPage.addActionListener(new HyperLink<>(new Lends(this.search,page+1)));
        }
        else{
            nextPage.setEnabled(false);
        }

        goBackButton.addActionListener(new HyperLink<>(new Menu()));

    }



    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }

}
