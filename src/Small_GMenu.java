import javax.swing.*;
import java.awt.*;



public class Small_GMenu extends JPanel {

    public final static String Menu = "Menu";
    public final static String Pause = "Pause";
    public int width;
    public int height;
    public int xmn;
    public int ymn;
    public int xps;
    public int yps;


    JButton menubutton;
    JButton pausebutton;

    public Small_GMenu(){
        createbutton();
        setDimensionButton(70,20);
    }

    private void createbutton(){
        this.setLayout(null);
       // this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        menubutton = new JButton(Menu);
        pausebutton = new JButton(Pause);
        add(menubutton);
        add(pausebutton);
    }

    public void setDimensionButton(int width, int height){
        menubutton.setBounds(0,0,width,height);
        pausebutton.setBounds(width,0,width,height);
    }
}
