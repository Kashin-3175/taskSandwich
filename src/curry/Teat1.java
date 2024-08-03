package curry;

import java.io.FileWriter;

public class Teat1 {
    public static void main(String[] args) {
        String fileName = "src/curry/test1.csv";
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write("こんにちは"+","+"おはよう"+","+"こんばんは");
            writer.close();

        }catch (Exception e2){
            e2.printStackTrace();
        }
    }
}
