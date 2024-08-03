package curry;

import java.io.BufferedReader;
import java.io.FileReader;

public class Test2 {
    public static void main(String[] args) {
        String fileName = "src/curry/test1.csv";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = reader.readLine()) != null){
                String[] strArr = line.split(",");

                for(String strArr1 : strArr){
                    System.out.println(strArr1);
                }
                //System.out.println(strArr[0]);
                //System.out.println(strArr[1]);
                //System.out.println(strArr[2]);





            }
        }catch (Exception e2){
            e2.printStackTrace();
        }
    }

}
