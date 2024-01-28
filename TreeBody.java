package TreeVsBubble;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class TreeBody {

    private TreeNode root;
    private int treeCompares;
    private int bubbleSortCompares;

    public TreeBody() {
        this.root = null;
        this.treeCompares = 0;
        this.bubbleSortCompares = 0;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private TreeNode insertNode(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        treeCompares++;
        if (data == current.data) {
            current.count++;
        } else if (data < current.data) {
            current.left = insertNode(current.left, data);
        } else {
            current.right = insertNode(current.right, data);
        }
        return current;
    }

    public void buildTreeFromArray(int[] numbers) {
        for (int number : numbers) {
            insert(number);
        }
    }

    public void inOrderTraversal(TreeNode current, FileWriter fileWriter) throws IOException {
        if (current != null) {
            inOrderTraversal(current.left, fileWriter);
            for (int i = 0; i < current.count; i++) {
                fileWriter.write(current.data + "\n");
            }
            inOrderTraversal(current.right, fileWriter);
        }
    }

    public int getTreeCompares() {
        return treeCompares;
    }

    public void resetTreeCompares() {
        treeCompares = 0;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void writeTreeToFile(TreeNode root, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            inOrderTraversal(root, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTreeIterativelyToFile(TreeNode root, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            iterativeInOrderTraversal(root, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iterativeInOrderTraversal(TreeNode root, FileWriter fileWriter) throws IOException {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            for (int i = 0; i < current.count; i++) {
                fileWriter.write(current.data + "\n");
            }
            current = current.right;
        }
    }

    public int[] bubbleSort(int[] numbers) {
        resetBubbleSortCompares();

        int n = numbers.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                bubbleSortCompares++;
                if (numbers[i - 1] > numbers[i]) {
                    int temp = numbers[i - 1];
                    numbers[i - 1] = numbers[i];
                    numbers[i] = temp;

                    swapped = true;
                }
            }
            n--;
        } while (swapped);
        return numbers;
    }

    public void writeSortedArrayToFile(int[] sortedNumbers, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (int number : sortedNumbers) {
                fileWriter.write(number + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeStatisticsToFile(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write("Comparisons using Tree:" + getTreeCompares() + "\n");
            fileWriter.write("Comparisons using Bubble Sort:" + bubbleSortCompares);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetBubbleSortCompares() {
        bubbleSortCompares = 0;
    }
}

