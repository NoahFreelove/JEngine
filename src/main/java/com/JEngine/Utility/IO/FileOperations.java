package com.JEngine.Utility.IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.Scanner;

import static com.JEngine.PrimitiveTypes.VeryPrimitiveTypes.Thing.LogError;

public class FileOperations {

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

    public static void stringArrToFile(String[] arr, String filepath)
    {
        try {
            FileWriter writer = new FileWriter(filepath);
            for(String str: arr) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e)
        {
            LogError(String.format("Could not load file: `%s`. (fileToStringArr)", filepath));
        }
    }

}
