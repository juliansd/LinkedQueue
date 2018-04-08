package edu.nyu.cs.jsd410;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CustomerFileReader {
	
	FileReader fileReader;
	BufferedReader bufferedReader;
	String line = null;

	try {
		fileReader = new FileReader(customerFile);
		bufferedReader = new BufferedReader(fileReader);
		
		while((line = bufferedReader.readLine()) != null) {
			System.out.println(line + " " + "*********");
		}
		
	} catch(FileNotFoundException e) {
        e.printStackTrace();                
        }
        catch(IOException e) {
            e.printStackTrace();                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	
}
	
}
