package compiler;

import java.io.*;

public class Utilities{

    public static void writeFile(byte[] bytearray, String fileName){

        try{
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(bytearray);
            out.close();
        }
        catch(IOException e){
        System.out.println(e.getMessage());
        }
        
    }//end writeFile

}//end class    
