import javax.swing.*;
import java.awt.*;



public class Small_GMenu extends JPanel {

    public final static String Menu = "Menu";
    public final static String Pause = "Pause";

    JButton menubutton;
    JButton pausebutton;

    public Small_GMenu(){
        createbutton();
    }

    private void createbutton(){
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); //LINE_AXIS

        menubutton = new JButton(Menu);
        pausebutton = new JButton(Pause);

        add(menubutton);
        add(pausebutton);
    }
}
