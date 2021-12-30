import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 24/12/2021 - 11:44
 * Description: ...
 */
public class MainGui {
    public static JFrame mainFrame;
    public static void CreateAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainFrame = new JFrame("Slang Word!!!");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainFrame.setContentPane(new FormPane());

        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public static void main(String[] argv) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Slang.ReadFile();
                CreateAndShowGUI();
            }
        });
    }
    public static class FormPane extends JPanel  {
        public FormPane() {
            setBorder(new EmptyBorder(5,5,5,5));
            setLayout(new BorderLayout());

            add(new JLabel("WELCOME TO SLANG PROGRAM!!!!"),BorderLayout.PAGE_START);
            add(new BtnPane(),BorderLayout.CENTER);
        }

    }
    public static class BtnPane extends JPanel implements ActionListener {
        // button
        JButton Find_Edit = new JButton("Find and Edit");
        JButton History = new JButton("History");
        JButton Add = new JButton("Add Slang");
        JButton Delete = new JButton("Delete Slang");
        JButton Reset = new JButton("Reset original");
        JButton Random = new JButton("Random");
        JButton Quiz = new JButton("Quiz");

        public void addlisten(){
            Find_Edit.addActionListener(this);
            Find_Edit.setActionCommand("find_edit");
            History.addActionListener(this);
            History.setActionCommand("history");
            Add.addActionListener(this);
            Add.setActionCommand("add");
            Delete.addActionListener(this);
            Delete.setActionCommand("delete");
            Reset.addActionListener(this);
            Reset.setActionCommand("reset");
            Random.addActionListener(this);
            Random.setActionCommand("random");
            Quiz.addActionListener(this);
            Quiz.setActionCommand("quiz");
        }

        public BtnPane() {
            setBorder(new EmptyBorder(5,5,5,5));
            setLayout(new GridLayout(4,2,5,5));

            addlisten();

            add(Find_Edit);
            add(History);
            add(Add);
            add(Delete);
            add(Reset);
            add(Random);
            add(Quiz);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if("find_edit".equals(e.getActionCommand())){
                FindEditGUI f = new FindEditGUI();
                mainFrame.setVisible(false);
            }
            if("history".equals(e.getActionCommand())){

            }
            if("add".equals(e.getActionCommand())){

            }
            if("delete".equals(e.getActionCommand())){

            }
            if("reset".equals(e.getActionCommand())){

            }
            if("random".equals(e.getActionCommand())){

            }
            if("quiz".equals(e.getActionCommand())){

            }
        }
    }
}
