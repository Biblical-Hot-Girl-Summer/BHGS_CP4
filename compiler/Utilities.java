package compiler;

import java.io.*;

/**
 * Writes results into a file.
 * @author Jacob Morris
 * @author Aaron Bone
 * @version 1.0
 * Compiler Assignment 4
 * CS322 - Compiler Construction
 * Fall 2021
 *
 */

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
