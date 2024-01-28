package TreeVsBubble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TreeMain {

    public static void main(String[] args) {
        String filePath = "C:\\390\\numbers.txt";

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("File does not exist: " + filePath);
            return;
        }

        int[] numbers = readNumbersFromFile(filePath);

        if (numbers.length == 0) {
            System.out.println("Failed to read numbers");
            return;
        }

        TreeBody treeBody = new TreeBody();
        treeBody.buildTreeFromArray(numbers);
        TreeNode root = treeBody.getRoot();

        treeBody.writeTreeToFile(root, "sorted_tree_recursive.txt");
        treeBody.writeTreeIterativelyToFile(root, "sorted_tree_iterative.txt");

        int[] sortedNumbers = treeBody.bubbleSort(numbers);
        treeBody.writeSortedArrayToFile(sortedNumbers, "sorted_bubble_sort.txt");

        treeBody.writeStatisticsToFile("statistics.txt");

    }

    private static int[] readNumbersFromFile(String filePath) {
        try {
            int count = 0;
            try (BufferedReader countReader = new BufferedReader(new FileReader(filePath))) {
                while (countReader.readLine() != null) {
                    count++;
                }
            }

            int[] numbers = new int[count];
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                for (int i = 0; i < count; i++) {
                    String line = reader.readLine();
                    numbers[i] = Integer.parseInt(line);
                }
            }
            return numbers;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return new int[0];
        }
    }
}

