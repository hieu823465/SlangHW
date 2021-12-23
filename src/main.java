import java.io.*;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 23/12/2021 - 16:39
 * Description: ...
 */
public class main {
    public static HashMap<String,String> slangword = new HashMap<>();
    public static ArrayList<String> history = new ArrayList<>();

    public static void ReadFile(){
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
        }
    }

    // tính năng 1
    public static String SearchDefinition(String slang){
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getKey().contains(slang)) {
                tmp.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
                history.add(entry.getKey() + " " + entry.getValue());
            }
        return tmp.toString();
    }

    // tính năng 2
    public static String SearchSlang(String value) {
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getValue().toLowerCase(Locale.ROOT).contains(value)) {
                tmp.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
                history.add(entry.getKey() + " " + entry.getValue());
            }
        return tmp.toString();
    }

    // tính năng 4
    public static void AddSlang(String slang, String value){
        String check = "";
        check = slangword.get(slang);
        // thông báo bị trùng key, overwrite hay duplicate hay khỏi làm

        if(!Objects.equals(check, "")){ // có slang trùng
            // overwrite
            //slangword.put(slang, value);

            // duplicate
            String old = slangword.get(slang);
            slangword.put(slang,old + " | " + value);
        }
        else { // không có slang trùng
            System.out.println("No slang");
            slangword.put(slang, value);
        }


    }

    public static void main(String[] argv){
        ReadFile();

        long start = System.currentTimeMillis();
        AddSlang("$","tien viet");
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println(elapsedTimeMillis + " ms");

        System.out.println(SearchDefinition("$"));

    }

}
