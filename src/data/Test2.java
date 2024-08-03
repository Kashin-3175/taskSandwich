package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Test2 {
    public static void main(String[] args) {
        String filePath = "src/data/avc.csv";

        try{
            FileWriter writer = new FileWriter(filePath);
            writer.write("aaa,bbb,ccc\n");
            writer.write("ddd,eee,fff\n");
            /*writer.append("aaa")
                    .append(",").append("bbb")
                    .append(",").append("ccc").append("\n");*/
            writer.close();
        }catch (Exception e2){
            e2.printStackTrace();
        }

        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;

            while((line = reader.readLine()) != null){
                String[] atrArr =line.split(",");

                for (String str : atrArr){
                    System.out.println(str);
                }
            }
        }catch (Exception e2){
            e2.printStackTrace();
        }


    }
}
