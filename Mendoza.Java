
import java.io.*;
import java.util.*;


public class Mendoza {
    public static void main(String[] args) {
        
        // Input and Output file names

        String inputFile = "MENDOZA.txt";
        String outputFile = "Yuan.txt";

        //using arrays for this project since given data sets are fixed

        double[] numbers = new double[15]; // fixed 15 numbers from the given data set
        String[] words = new String[15]; // fixed 15 words from the given data set

        int count = 0; // counter for the number of lines read

         // Read input file (Mendoza.txt) and store the numbers and words in arrays
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) // uses BufferReader to read the txt input file line by line
        {
            String line;

            while ((line = br.readLine()) != null && count < 15) // checks if the line is not null and count is less than 15 to avoid ArrayIndexOutOfBoundsException
            {
                
                if (line.trim().isEmpty()) // Skip empty lines
                {
                    continue;
                }

                else // Process non-empty lines
                {
                String[] parts = line.trim().split("\\s+", 2); // Split line into number and word

                numbers[count] = Double.parseDouble(parts[0]);
                words[count] = parts[1].trim();
                count++;
                }

               
            }
        } 
        catch (IOException e) 
        
        {
            System.out.println("Error reading input file: " + e.getMessage()); // Print error message if there's an issue reading the input file
            return;
        }


        // Sort numbers descending (highest to lowest)
        // Boxing needed since Arrays.sort(double[]) only sorts ascending
        Double[] boxedNumbers = new Double[count];

        for (int i = 0; i < count; i++)   // Loop through the numbers array and box each double into a Double object
        {
     boxedNumbers[i] = numbers[i];
        }

        Arrays.sort(boxedNumbers, Collections.reverseOrder()); // Sort AFTER the array is fully boxed, otherwise it sorts while still half-empty (nulls -> NullPointerException)

        // Sort words by length ascending (shortest to longest)
        Arrays.sort(words, 0, count, Comparator.comparingInt(String::length));



          try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) // uses BufferWriter to write the output to a txt file
          {
            for (int i = 0; i < count; i++) // Loop through the arrays and write the number, word, and length of the word to the output file
            {

                // this data variables is used to store the number, word, and length of the word for each line in the output file

                String num = String.format("%.3f", boxedNumbers[i]);
                String word = words[i];
                int length = word.length();

                bw.write(String.format("%-20s%-40s%-5d", num, word, length));
                bw.newLine();
            }
            System.out.println("Output file generated: " + outputFile);
            } 
            
        catch (IOException e) 
        {
            System.out.println("Error writing output file: " + e.getMessage()); // Print error message if there's an issue writing the output file
        }
    }

}