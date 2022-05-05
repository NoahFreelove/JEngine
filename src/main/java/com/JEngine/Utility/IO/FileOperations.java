package com.JEngine.Utility.IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.JEngine.PrimitiveTypes.Thing.LogError;

/** FileOperations (c) Noah Freelove
 * Lets you easily read and write strings to files
 */

public class FileOperations {

    /**
     * Reads a file and returns it as a string array
     * @param filepath The filepath to the file
     * @return The file as a string array
     */
    public static String[] fileToStringArr(String filepath)
    {
        Path path = Paths.get(filepath);
        String[] retArr;

        int lines;
        try {

            lines = (int) Files.lines(path).parallel().count();
            retArr = new String[lines];

            Scanner s = new Scanner(new FileReader(filepath));
            int i = 0;

            while (s.hasNext()) {
                retArr[i] = s.nextLine();
                i++;
            }

            s.close();
            return retArr;

        } catch (IOException e) {
            LogError(String.format("Could not load file: `%s`. (fileToStringArr)", filepath));
        }
        return new String[] {""};
    }

    /**
     * Writes a string array to a file
     * @param arr The string array to write
     * @param filepath The filepath to the file
     */
    public static void stringArrToFile(String[] arr, String filepath)
    {
        try {
            FileWriter writer = new FileWriter(filepath);
            for(String str: arr) {
                if(str == null)
                    continue;
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e)
        {
            LogError(String.format("Could not load or write to file: `%s`. (stringArrToFile)", filepath));
        }
    }

}
