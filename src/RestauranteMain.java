import javax.swing.*;
import java.awt.*;

public class RestauranteMain {

    public static void main(String args[]){

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm principalForm = new mainForm();
                principalForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                principalForm.setBounds(200,200,800,500);
                principalForm.setContentPane(principalForm.getMainPanel());
                principalForm.setVisible(true);
            }
        });
    }
}
