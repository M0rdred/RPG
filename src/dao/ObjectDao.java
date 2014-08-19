/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

/**
 *
 * @author dszabo1
 */
public class ObjectDao {
    
    protected static ObjectMapper objectMapper = new ObjectMapper();
    
    protected void contactFile(String resource){
        
    }

    protected String scanRow(String row, int numberOfAttribute) {
        String mode = null;
        Scanner rowScanner = new Scanner(row);
        rowScanner.useDelimiter(";");
        int counter = 0;
        numberOfAttribute++;

        while (rowScanner.hasNext()) {
            mode = rowScanner.next();

            if (numberOfAttribute == counter++) {
                break;
            }
        }
        return mode;
    }
}
