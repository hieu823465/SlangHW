import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 31/12/2021 - 14:30
 * Description: ...
 */
public class QuizGUI {
    public static JFrame frame;
    public QuizGUI(int game_mode) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GUI gui = new GUI(game_mode);
        frame.add(gui);
        frame.pack();
        frame.setVisible(true);
    }

    public static class GUI extends JPanel {
        // component
        int mode; // 0 : quiz slang -> 1: quiz definition

        int answer;
        JLabel question = new JLabel();

        // đối chiếu với answer thì đáp án A là 0, B là 1, C là 2, D là 3
        JButton A = new JButton();
        JButton B = new JButton();
        JButton C = new JButton();
        JButton D = new JButton();

        JButton back = new JButton("go back");

        public void addListener() {
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainGui.mainFrame.setVisible(true);
                }
            });
            A.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(answer == 0){
                        // trả lời đúng
                        JOptionPane.showMessageDialog(
                                frame,
                                "You got the correct answer!!",
                                "Congrats",
                                JOptionPane.INFORMATION_MESSAGE);
                        if(mode == 0) { // quiz lang
                            String[] split = Slang.Quiz_slang().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                        else if(mode == 1) { // quiz defi
                            String[] split = Slang.Quiz_Defi().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                    }else {
                        // trả lời sai
                        JOptionPane.showMessageDialog(
                                frame,
                                "Wrong answer. ",
                                "Sorry:(",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            B.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(answer == 1){
                        // trả lời đúng
                        JOptionPane.showMessageDialog(
                                frame,
                                "You got the correct answer!!",
                                "Congrats",
                                JOptionPane.INFORMATION_MESSAGE);
                        if(mode == 0) { // quiz lang
                            String[] split = Slang.Quiz_slang().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                        else if(mode == 1) { // quiz defi
                            String[] split = Slang.Quiz_Defi().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                    }else {
                        // trả lời sai
                        JOptionPane.showMessageDialog(
                                frame,
                                "Wrong answer. ",
                                "Sorry:(",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            C.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(answer == 2){
                        // trả lời đúng
                        JOptionPane.showMessageDialog(
                                frame,
                                "You got the correct answer!!",
                                "Congrats",
                                JOptionPane.INFORMATION_MESSAGE);
                        if(mode == 0) { // quiz lang
                            String[] split = Slang.Quiz_slang().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                        else if(mode == 1) { // quiz defi
                            String[] split = Slang.Quiz_Defi().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                    }else {
                        // trả lời sai
                        JOptionPane.showMessageDialog(
                                frame,
                                "Wrong answer. ",
                                "Sorry:(",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            D.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(answer == 3){
                        // trả lời đúng
                        JOptionPane.showMessageDialog(
                                frame,
                                "You got the correct answer!!",
                                "Congrats",
                                JOptionPane.INFORMATION_MESSAGE);
                        if(mode == 0) { // quiz lang
                            String[] split = Slang.Quiz_slang().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                        else if(mode == 1) { // quiz defi
                            String[] split = Slang.Quiz_Defi().split("\t");
                            question.setText(split[0]);
                            MixAnswer(split);
                        }
                    }else {
                        // trả lời sai
                        JOptionPane.showMessageDialog(
                                frame,
                                "Wrong answer. ",
                                "Sorry:(",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        public GUI(int game_mode) {
            mode = game_mode;
            setLayout(new BorderLayout());
            addListener();
            setPreferredSize(new Dimension(400,200));

            JPanel pane = new JPanel();
            pane.setLayout(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();
            g.gridx = 0;
            g.gridy = 0;
            JLabel title = new JLabel("WORD OF THE DAY");
            pane.add(title,g);
            g.gridy = 1;
            pane.add(question,g);


            JPanel quiz = new JPanel();
            quiz.setLayout(new GridLayout(2,2,2,2));
            quiz.add(A);
            quiz.add(B);
            quiz.add(C);
            quiz.add(D);

            add(pane,BorderLayout.BEFORE_FIRST_LINE);
            add(quiz,BorderLayout.CENTER);
            add(back,BorderLayout.AFTER_LAST_LINE);

            // chạy chương trình khi khởi động
            if(game_mode == 0) { // quiz slang
                // câu hỏi -> đáp án -> 3 đáp án khác
                String[] split = Slang.Quiz_slang().split("\t");
                question.setText(split[0]);
                MixAnswer(split);
            }
            else if(game_mode == 1) { // quiz definition
                // câu hỏi -> đáp án -> 3 đáp án khác
                String[] split = Slang.Quiz_Defi().split("\t");
                question.setText(split[0]);
                MixAnswer(split);
            }
        }
        public void MixAnswer(String[] split) {
            int random = new Random().nextInt(4);
            answer = random;
            String answer = split[1];
            switch (random){
                case 0 -> {
                    // answer a
                    A.setText(answer);
                    B.setText(split[2]);
                    C.setText(split[3]);
                    D.setText(split[4]);
                    break;
                }
                case 1 -> {
                    // answer b
                    A.setText(split[2]);
                    B.setText(answer);
                    C.setText(split[3]);
                    D.setText(split[4]);
                    break;
                }
                case 2 -> {
                    // answer c
                    A.setText(split[2]);
                    B.setText(split[3]);
                    C.setText(answer);
                    D.setText(split[4]);
                    break;
                }
                case 3 -> {
                    // answer d
                    A.setText(split[2]);
                    B.setText(split[3]);
                    C.setText(split[4]);
                    D.setText(answer);
                    break;
                }
            }
        }
    }
}
