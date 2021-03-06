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
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JButton Find_Edit = new JButton("Find,Edit and Delete");
        JButton History = new JButton("History");
        JButton Add = new JButton("Add Slang");
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
            add(Reset);
            add(Random);
            add(Quiz);

            FindEditDeleteGUI findedit = new FindEditDeleteGUI();

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if("find_edit".equals(e.getActionCommand())){
                FindEditDeleteGUI.frame.setVisible(true);
                mainFrame.setVisible(false);
            }
            if("history".equals(e.getActionCommand())){
                HistoryGUI history = new HistoryGUI();
                mainFrame.setVisible(false);
            }
            if("add".equals(e.getActionCommand())){
                AddSlangGUI add = new AddSlangGUI();
                mainFrame.setVisible(false);
            }
            if("reset".equals(e.getActionCommand())){
                if(Slang.ResetSlang()){
                    JOptionPane.showMessageDialog(
                            mainFrame,
                            "Restore original list successfully",
                            "Notify",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(
                            mainFrame,
                            "Restore original list FAILED!!!",
                            "Notify",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if("random".equals(e.getActionCommand())){
                RandomGUI random = new RandomGUI();
                mainFrame.setVisible(false);
            }
            if("quiz".equals(e.getActionCommand())){
                JDialog dialog = new JDialog(mainFrame,"Choose Quiz");
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setPreferredSize(new Dimension(350,70));
                dialog.setLayout(new FlowLayout(FlowLayout.LEFT));
                JButton slang_quiz = new JButton("Slang quiz");
                JButton defi_quiz = new JButton("Definition quiz");
                dialog.add(slang_quiz);
                dialog.add(defi_quiz);
                dialog.setVisible(true);
                dialog.pack();
                slang_quiz.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 0 : quiz slang -> 1: quiz definition
                        QuizGUI quiz = new QuizGUI(0);
                        dialog.dispose();
                        mainFrame.setVisible(false);
                    }
                });
                defi_quiz.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 0 : quiz slang -> 1: quiz definition
                        QuizGUI quiz = new QuizGUI(1);
                        dialog.dispose();
                        mainFrame.setVisible(false);
                    }
                });
            }
        }
    }
}
