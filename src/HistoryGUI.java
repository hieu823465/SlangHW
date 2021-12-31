import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 31/12/2021 - 09:44
 * Description: ...
 */
public class HistoryGUI {
    public static JFrame frame;
    public HistoryGUI() {
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
        public static JTable table = new JTable();
        public static DefaultTableModel model = new DefaultTableModel();

        public static JButton back = new JButton("back");

        public GUI() {
            setLayout(new BorderLayout());

            String[] columns = {"Slang","Meaning"};
            model.setColumnIdentifiers(columns);
            model.setRowCount(0);

            table.setModel(model);

            HistoryGUI.GUI.model.setRowCount(0);
            for(int i = 0 ; i < Slang.history.size(); i+=2){
                String[] split = Slang.history.get(i).split("\t");
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        HistoryGUI.GUI.model.addRow(new Object[]{split[0],split[1]});
                    }
                });
            }

            JScrollPane scroll = new JScrollPane(table);

            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainGui.mainFrame.setVisible(true);
                }
            });

            add(new JLabel("History"),BorderLayout.PAGE_START);
            add(scroll,BorderLayout.CENTER);
            add(back,BorderLayout.AFTER_LAST_LINE);


        }
    }
}
