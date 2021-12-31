import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Objects;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 24/12/2021 - 12:12
 * Description: ...
 */
public class FindEditDeleteGUI {
    public static JFrame frame;
    public FindEditDeleteGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GUI gui = new GUI();
        frame.add(gui);
        frame.pack();
    }

    public static class GUI extends JPanel {
        // component
        public static JLabel time = new JLabel("TIME SPENT: 0ms");
        public static JTextField input = new JTextField(20);

        public static final JButton find = new JButton("Find");
        public static final JButton delete = new JButton("Delete");
        public static final JButton edit = new JButton("Edit");
        public static final JButton back = new JButton("Back");

        public static JTable table = new JTable();
        public static DefaultTableModel model = new DefaultTableModel();


        find f = new find();
        edit e = new edit();

        public void addListener() {
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String check = Slang.slangword.get(input.getText());
                    if(Objects.equals(check,null)){
                        JOptionPane.showMessageDialog(
                                frame,
                                "Word not found",
                                "Warning",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        int result = JOptionPane.showConfirmDialog(frame,
                                "Do you want to delete!!!",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                        if(result == JOptionPane.YES_OPTION){
                            Slang.DeleteSlang(input.getText());
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Deleted",
                                    "Notify",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            find.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   f.setVisible(true);
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ee) {
                    String check = Slang.slangword.get(input.getText());
                    if(Objects.equals(check,null)){
                        JOptionPane.showMessageDialog(
                                frame,
                                "Word not found",
                                "Warning",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        e.slang.setText(input.getText());
                        e.defi.setText(Slang.slangword.get(input.getText()));
                        e.setVisible(true);
                    }
                }
            });
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    MainGui.mainFrame.setVisible(true);
                }
            });

        }
        public GUI() {
            addListener();
            f.setVisible(false);
            e.setVisible(false);
            setLayout(new GridBagLayout());

            JPanel tableFunc = new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();

            JPanel title = new JPanel();
            title.setLayout(new BoxLayout(title,BoxLayout.Y_AXIS));
            JLabel label = new JLabel("Find,Edit and Delete Functionality!");
            title.add(label);
            title.add(time);

            JPanel func = new JPanel(new FlowLayout(FlowLayout.LEFT));
            func.add(new JLabel("Input:"));
            func.add(input);
            func.add(find);
            func.add(edit);
            func.add(delete);

            g.gridx = 0;
            g.gridy = 0;
            tableFunc.add(title,g);

            g.gridy = 1;
            tableFunc.add(func,g);


            JPanel tablePane = new JPanel(new GridBagLayout());
            g = new GridBagConstraints();
            String[] columns = {"Slang","Meaning"};
            model.setColumnIdentifiers(columns);
            model.setRowCount(0);

            table.setModel(model);
            JScrollPane scroll = new JScrollPane(table);

            g.fill = GridBagConstraints.REMAINDER;
            g.weightx = 0.5;
            g.weighty = 0.5;
            tablePane.add(scroll,g);

            g.gridy = 1;
            tablePane.add(back,g);

            g = new GridBagConstraints();
            g.gridx = 0;
            g.gridy = 0;
            add(tableFunc,g);

            g.fill = GridBagConstraints.REMAINDER;
            g.gridy = 1;
            add(tablePane,g);

        }
    }

    public static class find extends JDialog {
        JButton slang = new JButton("Search Definition");
        JButton definition = new JButton("Search Slang");

        public find() {
            super(frame,"Choose");
            setPreferredSize(new Dimension(300,80));
            pack();
            setVisible(true);
            setLayout(new FlowLayout(FlowLayout.LEFT));
            add(slang);
            add(definition);
            slang.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long startTime = System.currentTimeMillis();
                    String list = Slang.SearchDefinition(GUI.input.getText());
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    GUI.time.setText("TIME SPENT: " + estimatedTime + "ms");

                    String[] split = list.split("\t");
                    GUI.model.setRowCount(0);
                    for(int i = 0 ; i < split.length - 1 ; i+=2){
                        int finalI = i;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                GUI.model.addRow(new Object[]{split[finalI],split[finalI +1]});
                            }
                        });
                    }
                    setVisible(false);
                }
            });
            definition.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long startTime = System.currentTimeMillis();
                    String list = Slang.SearchSlang(GUI.input.getText());
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    GUI.time.setText("TIME SPENT: " + estimatedTime + "ms");

                    String[] split = list.split("\t");
                    GUI.model.setRowCount(0);
                    for(int i = 0 ; i < split.length - 1 ; i+=2){
                        int finalI = i;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                GUI.model.addRow(new Object[]{split[finalI],split[finalI +1]});
                            }
                        });
                    }
                    setVisible(false);
                }
            });
        }
    }

    public static class edit extends JDialog {


        JTextField slang = new JTextField(10);
        JTextField defi = new JTextField(10);

        JButton back = new JButton("back");
        JButton edit = new JButton("Edit");

        public void addListener() {
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Slang.EditSlang(slang.getText(),defi.getText());
                    JOptionPane.showMessageDialog(
                            frame,
                            "Editted",
                            "Notify",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    setVisible(false);
                }
            });
        }
        public edit() {
            super(frame,"Edit");

            addListener();

            setPreferredSize(new Dimension(300,120));
            pack();
            setVisible(true);
            setLayout(new BorderLayout());

            JPanel main = new JPanel(new GridLayout(2,2,2,2));
            main.add(new JLabel("Slang"));
            main.add(new JLabel("Definition"));
            main.add(slang);
            main.add(defi);

            JPanel btn = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btn.add(back);
            btn.add(edit);

            add(main,BorderLayout.CENTER);
            add(btn,BorderLayout.AFTER_LAST_LINE);

        }
    }
}
