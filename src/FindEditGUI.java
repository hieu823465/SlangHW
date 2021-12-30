import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 24/12/2021 - 12:12
 * Description: ...
 */
public class FindEditGUI {
    public static JFrame frame;
    public FindEditGUI() {
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
        public static JTextField input = new JTextField(20);

        JButton find = new JButton("Find");
        JButton edit = new JButton("Edit");
        JButton back = new JButton("Back");

        public static JTable table = new JTable();
        public static DefaultTableModel model = new DefaultTableModel();

        public void addListener() {
            find.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    find f = new find();
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

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
            setLayout(new GridBagLayout());

            JPanel tableFunc = new JPanel(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();

            JPanel title = new JPanel();
            JLabel label = new JLabel("Find And Edit Functionality!");
            title.add(label);

            JPanel func = new JPanel(new FlowLayout(FlowLayout.LEFT));
            func.add(new JLabel("Input:"));
            func.add(input);
            func.add(find);
            func.add(edit);

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
                    String list = Slang.SearchDefinition(GUI.input.getText());
                    String[] split = list.split("\t");
                    GUI.model.setRowCount(0);
                    for(int i = 0 ; i < split.length - 1 ; i+=2){
                        System.out.println(split[i] +  split[i+1] );
                        int finalI = i;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                GUI.model.addRow(new Object[]{split[finalI],split[finalI +1]});
                            }
                        });
                    }
                    dispose();
                }
            });
        }
    }
    //public static void main(String[] argv) {FindEditGUI f = new FindEditGUI();}
}
