package view;

import business.BookBusiness;
import dto.Book;
import view.component.BookComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Books implements Viewable{
    private JPanel title;
    private JLabel titleLabel;
    private JPanel body;
    private JPanel content;
    private JButton goBackButton;
    private JTextField searchField;
    private JPanel booksPanel;
    private JButton backPage;
    private JButton nextPage;
    private final int page;
    private final String search;

    public Books(String search, int page){
        this.search = search;
        this.page = page;
    }

    private void makeFunctional(){
        System.out.println(this.search);
        searchField.setText(this.search);
        booksPanel.setLayout(new BoxLayout(booksPanel,BoxLayout.Y_AXIS));
        goBackButton.addActionListener(new HyperLink<>(new Menu()));
        BookBusiness bookBusiness = new BookBusiness();
        List<Book> books = bookBusiness.selectAll(this.search,this.page);
        for(Book book : books){
            if(book.available_copies() > 1){
                booksPanel.add(new BookComponent(book));
            }
        }
        booksPanel.revalidate();
        booksPanel.repaint();
        content.revalidate();
        content.repaint();
        booksPanel.setVisible(true);
        if(this.page > 0){
            backPage.addActionListener(new HyperLink<>(new Books(this.search,page-1)));
        }else{
            backPage.setEnabled(false);
        }
        if(this.page < bookBusiness.getTotalPages()){
            nextPage.addActionListener(new HyperLink<>(new Books(this.search,page+1)));
        }
        else{
            nextPage.setEnabled(false);
        }
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                Window.goTo(new Books(search,0));
            }
        });

    }

    @Override
    public JPanel getContent(){
        makeFunctional();
        return content;
    }

}
