import java.io.*;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by trunghieu
 * Date 23/12/2021 - 16:39
 * Description: ...
 */
public class main {
    // Chức năng tìm kiếm theo slang word.
    public static HashMap<String,String> slangword = new HashMap<>();

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
        //System.out.println(slangword.get(slang));
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getKey().contains(slang))
                tmp.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        return tmp.toString();
    }

    // tính năng 2
    public static String getKey(String value)
    {
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, String> entry: slangword.entrySet())
            if (entry.getValue().toLowerCase(Locale.ROOT).contains(value))
                tmp.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        return tmp.toString();
    }

    public static void main(String[] argv){
        ReadFile();

        long start = System.currentTimeMillis();
        System.out.println(SearchDefinition("#1"));
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println(elapsedTimeMillis + " ms");

    }

}
