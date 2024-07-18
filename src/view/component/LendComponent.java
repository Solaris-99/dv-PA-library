package view.component;
import dto.Lend;
import helpers.Status;

import javax.swing.*;

public class LendComponent extends JPanel {

    public LendComponent(Lend lend){
        this.add(new JLabel(""+ lend.time()));
        this.add(new JLabel(lend.getBookTitle()));
        if(Status.getInstance().isEmployee()){
            this.add(new JLabel(lend.getUserIdentity()));
        }
        String returnDate = "...";
        if(lend.return_date() != null){
            returnDate = ""+lend.return_date();
        }
        this.add(new JLabel(returnDate));
        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

}
