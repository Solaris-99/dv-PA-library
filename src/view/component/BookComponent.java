package view.component;

import business.LendBusiness;
import dto.Book;
import helpers.Status;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookComponent extends JPanel {
    private JLabel bookLabel;
    private JButton makeLend;

    public BookComponent(Book book){
        LendBusiness lendBusiness = new LendBusiness();
        bookLabel = new JLabel(book.getAuthor().name() +" - " +book.title());
        Border border = BorderFactory.createTitledBorder(book.getPublisher().name() +", "+book.year());
        this.setBorder(border);
        this.add(bookLabel);
        if(!lendBusiness.isLent(book.id()) && !Status.getInstance().isEmployee()){
            makeLend = new JButton("Pedir");
            this.add(makeLend);
            makeLend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lendBusiness.makeLend(Status.getInstance().getUserId(), book.id());
                    makeLend.getParent().remove(makeLend);
                }
            });
        }
        this.revalidate();
        this.repaint();

    }


}
