import javax.swing.*;
import java.awt.image.Kernel;
import java.io.*;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 23/12/2021 - 16:39
 * Description: ...
 */
public class Slang {
    public static HashMap<String,String> slangword = new HashMap<>();
    // tính năng 3
    public static ArrayList<String> history = new ArrayList<>();

    public static boolean ReadFile(){
        try {
            FileReader fr = new FileReader("slang.txt");
            BufferedReader br = new BufferedReader(fr);
            String tmp = br.readLine();
            while(true){
                tmp = br.readLine();
                if(tmp == null) break;
                String[] split = tmp.split("`");
                if(split.length == 2)
                    slangword.put(split[0],split[1]);
            }
        }catch (IOException f){
            System.out.println("wrong here!!!");
            return false;
        }
        return true;
    }

    // tính năng 1
    public static String SearchDefinition(String slang){
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getKey().contains(slang)) {
                tmp.append(entry.getKey()).append("\t").append(entry.getValue()).append("\t");
                history.add(entry.getKey() + "\t" + entry.getValue());
            }
        return tmp.toString();
    }

    // tính năng 2
    public static String SearchSlang(String value) {
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getValue().toLowerCase(Locale.ROOT).contains(value)) {
                tmp.append(entry.getKey()).append("\t").append(entry.getValue()).append("\t");
                history.add(entry.getKey() + "\t" + entry.getValue());
            }
        return tmp.toString();
    }

    // tính năng 4
    public static void AddSlang(JFrame frame,String slang, String value){
        String check = "";
        check = slangword.get(slang);
        // thông báo bị trùng key, overwrite hay duplicate hay khỏi làm

        if(!Objects.equals(check, null)){ // có slang trùng
            Object[] options = {"Overwrite", "Duplicate", "Cancel"};
            int result = JOptionPane.showOptionDialog(frame,
                    "Slang existed. Please choose option!!!",
                    "Xác nhận",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(result == JOptionPane.YES_OPTION){
                //overwrite
                slangword.put(slang, value);
            }else if (result == JOptionPane.NO_OPTION){
                // duplicate
                String old = slangword.get(slang);
                slangword.put(slang,old + " | " + value);
            } else if (result == JOptionPane.CANCEL_OPTION) {

            }
        }
        else { // không có slang trùng
            JOptionPane.showMessageDialog(
                    frame,
                    "Added successfully",
                    "Notify",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.out.println("No slang");
            slangword.put(slang, value);
        }
        frame.dispose();
        MainGui.mainFrame.setVisible(true);
    }

    // tính năng 5
    public static void EditSlang(String slang, String value) {
        String check = "";
        check = slangword.get(slang);
        if(!Objects.equals(check,null)) {
            slangword.put(slang,value);
        }
        else {
            System.out.println("not found slang word");
        }
    }

    // tính năng 6
    public static void DeleteSlang(String slang){
        String check = "";
        check = slangword.get(slang);
        if(!Objects.equals(check,null)) {
            slangword.remove(slang);
        }
        else {
            System.out.println("no slang word~~~!!!");
        }
    }

    // tính năng 7
    public static boolean ResetSlang() {
        return ReadFile();
    }

    // tính năng 8
    public static String[] RandomSlang() {
        List<String> keyList = new ArrayList<>(slangword.keySet());

        int random = new Random().nextInt(keyList.size());

        String random_key = keyList.get(random);
        String random_value = slangword.get(random_key);

        String[] word = {random_key,random_value};
        return word;

    }

    // tính năng 9
    public static String Quiz_slang() {
        List<String> keyList = new ArrayList<>(slangword.keySet());

        int random = new Random().nextInt(keyList.size());

        String random_key = keyList.get(random);
        String random_value = slangword.get(random_key);

        System.out.println("Key: " + random_key);

        // random các đáp án khác
        random = new Random().nextInt(keyList.size());
        String option1 = slangword.get(keyList.get(random));

        random = new Random().nextInt(keyList.size());
        String option2 = slangword.get(keyList.get(random));

        random = new Random().nextInt(keyList.size());
        String option3 = slangword.get(keyList.get(random));

        // câu hỏi -> đáp án -> 3 đáp án khác
        String tmp = random_key + "\t" + random_value + "\t" + option1 + "\t" + option2 + "\t" + option3;;
        return tmp;
    }

    // tính năng 10
    public static String Quiz_Defi() {
        List<String> keyList = new ArrayList<>(slangword.keySet());

        int random = new Random().nextInt(keyList.size());

        String random_key = keyList.get(random);
        String random_value = slangword.get(random_key);

        System.out.println("Definition: " + random_value);

        // random các đáp án khác
        random = new Random().nextInt(keyList.size());
        String option1 = keyList.get(random);

        random = new Random().nextInt(keyList.size());
        String option2 = keyList.get(random);

        random = new Random().nextInt(keyList.size());
        String option3 = keyList.get(random);

        // câu hỏi -> đáp án -> 3 đáp án khác
        String tmp = random_value + "\t" + random_key + "\t" + option1 + "\t" + option2 + "\t" + option3;
        return tmp;
    }

//    public static void main(String[] argv) {
//        ReadFile();
//        System.out.println(slangword.get(":)))"));
//    }
}
