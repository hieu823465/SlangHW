import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 31/12/2021 - 11:19
 * Description: ...
 */
public class RandomGUI {
    public static JFrame frame;
    public RandomGUI() {
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
        JLabel key = new JLabel("None");
        JLabel value = new JLabel("None");

        JButton random = new JButton("Random");
        JButton back = new JButton("Go back");

        public void addListener() {
            random.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] word = Slang.RandomSlang();
                    key.setText(word[0]);
                    value.setText(word[1]);
                }
            });
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainGui.mainFrame.setVisible(true);
                }
            });
        }
        public GUI() {
            addListener();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(400,250));

            JPanel top = new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();
            JLabel title = new JLabel("WORD OF THE DAY!!!");
            top.add(title,g);

            JPanel quiz = new JPanel();
            quiz.setLayout(new GridBagLayout());

            g.gridx = 0;
            g.gridy = 0;
            quiz.add(new JLabel("SLANG: "),g);

            g.gridx = 1;
            quiz.add(key,g);

            g.gridy = 1;
            g.gridx = 0;
            quiz.add(new JLabel("DEFI: "),g);

            g.gridx = 1;
            quiz.add(value,g);


            JPanel btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btn.add(back);
            btn.add(random);

            add(top,BorderLayout.PAGE_START);
            add(quiz,BorderLayout.CENTER);
            add(btn,BorderLayout.AFTER_LAST_LINE);



        }
    }


}
