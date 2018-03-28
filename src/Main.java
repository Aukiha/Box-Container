// Author: Shane Williams
// Date: March 28, 2018
// Purpose: Find the 5 best containers within the given parameters

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
public class Main {

    // Array to hold the volumes of the top 5 containers
    static int[] containerVolumes = new int[5];

    // Array to hold the dimensions of the top 5 containers
    static String[] bestContainers = new String[5];

    // Boolean value that tracks whether any of the containers are suitable
    static Boolean tracker = false;
    // Counter that keeps track of the position to place containers in the top 5 array
    static int counter = 0;

    public static void main(String[] args) {

        // int variables to hold the dimensions of the containers
        int length;
        int width;
        int height;
        int volume;

        // BufferedReader that will read the file holding the containers' dimensions
        BufferedReader fileRead;
        try {
            // Read the file "containers.txt"
            File containerFile = new File("containers.txt");
            fileRead = new BufferedReader(new FileReader(containerFile));

            // Read the current line in the value and assign it to current line
            String currentLine;
            while((currentLine = fileRead.readLine()) != null) {

                // Splits the line by the commas to easily access the seperate dimensions
                String[] line = currentLine.split(",");

                // Converts the dimensions from strings to integers and assigns them to the corresponding variable
                length = Integer.parseInt(line[0]);
                width = Integer.parseInt(line[1]);
                height = Integer.parseInt(line[2]);

                // Calculates the container's volume using the dimensions
                volume = length * width * height;

                // Checks if the container falls within the parameters wanted
                if(volume >= 375 && volume <= 425) {

                    // Assigns the container to a position in the bestContainers array
                    if(counter == 0) {
                        bestContainers[counter] = currentLine;
                        containerVolumes[0] = volume;
                        tracker = true;
                    }
                    if(counter == 1) {
                        bestContainers[counter] = currentLine;
                        containerVolumes[1] = volume;
                    }
                    if(counter == 2) {
                        bestContainers[counter] = currentLine;
                        containerVolumes[2] = volume;
                    }
                    if(counter == 3) {
                        bestContainers[counter] = currentLine;
                        containerVolumes[3] = volume;
                    }
                    if(counter == 4) {
                        bestContainers[counter] = currentLine;
                        containerVolumes[4] = volume;
                    }

                    // if the list is full...
                    if(counter == 5) {

                        // Sorts the containerVolumes from least to greatest
                        Arrays.sort(containerVolumes);

                        // Checks if the current container can replace another container in the array
                        for(int x = 4; x >= 0; x--) {
                            if(volume < containerVolumes[x]) {
                                bestContainers[x] = currentLine;
                                containerVolumes[x] = volume;
                                break;
                            }
                        }
                    }
                }
            }

            // if no suitable boxes were found
            if(tracker == false) {
                System.out.println("No suitable boxes are available.");
            }
            else {

                // Print the top 5 containers
                System.out.println("Best Boxes Available (Top 5)");
                for(int x = 0; x < 5; x++) {

                    // if the current position is not filled
                    if(bestContainers[x] == null) {

                        // no positions underneath it will be filled, so exit the loop
                        break;
                    }
                    else {

                        // Print the dimensions and volumes of the container
                        System.out.println("Dimensions: " + bestContainers[x]);
                        System.out.println("Volume: " + containerVolumes[x]);
                    }
                }
            }
        }
        catch(IOException e) {

        }
    }
}
