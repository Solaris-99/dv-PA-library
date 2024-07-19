package view.component;
import business.LendBusiness;
import dto.Lend;
import helpers.Status;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LendComponent extends JPanel {

    public LendComponent(Lend lend){
        this.add(new JLabel(lend.getBookTitle()));
        boolean returned = lend.return_date() != null;

        Border border = BorderFactory.createTitledBorder(lend.time() +" - " + ((returned)?lend.return_date()+"":"No devuelto"));
        this.setBorder(border);
        if(Status.getInstance().isEmployee()){
            this.add(new JLabel(" "+lend.getUserIdentity()));
            if(!returned){
                JButton markAsReturned = new JButton("Marcar Devuelto");
                markAsReturned.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        LendBusiness lendBusiness = new LendBusiness();
                        lendBusiness.markAsReturned(lend);
                    }
                });
                this.add(markAsReturned);
            }
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

}
