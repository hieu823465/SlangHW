import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 31/12/2021 - 10:00
 * Description: ...
 */
public class AddSlangGUI {
    public static JFrame frame;
    public AddSlangGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GUI gui = new GUI();
        frame.add(gui);
        frame.pack();
        frame.setVisible(true);
    }

    public static class GUI extends JPanel {
        // component
        JTextField slang = new JTextField(10);
        JTextField defi = new JTextField(10);

        JButton back = new JButton("Go back");
        JButton add = new JButton("Add");


        public GUI() {
            setPreferredSize(new Dimension(300,120));
            setLayout(new BorderLayout());

            JPanel main = new JPanel(new GridLayout(2,2,2,2));
            main.add(new JLabel("Slang"));
            main.add(new JLabel("Definition"));
            main.add(slang);
            main.add(defi);

            JPanel btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btn.add(back);
            btn.add(add);

            add(main,BorderLayout.CENTER);
            add(btn,BorderLayout.AFTER_LAST_LINE);

            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainGui.mainFrame.setVisible(true);
                }
            });

            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Slang.AddSlang(frame,slang.getText(),defi.getText());
                }
            });




        }
    }
}
