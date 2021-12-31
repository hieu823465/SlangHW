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


            JLabel title = new JLabel("WORD OF THE DAY!!!");
            add(title,BorderLayout.PAGE_START);

            JPanel quiz = new JPanel();
            quiz.setLayout(new BoxLayout(quiz,BoxLayout.Y_AXIS));

            JPanel quiz_key = new JPanel(new FlowLayout(FlowLayout.LEFT));
            quiz_key.add(new JLabel("SLANG: "));
            quiz_key.add(key);

            JPanel quiz_value = new JPanel(new FlowLayout(FlowLayout.LEFT));
            quiz_value.add(new JLabel("DEFI: "));
            quiz_value.add(value);

            quiz.add(quiz_key);
            quiz.add(quiz_value);

            add(quiz,BorderLayout.CENTER);

            JPanel btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btn.add(back);
            btn.add(random);

            add(btn,BorderLayout.AFTER_LAST_LINE);



        }
    }


}
