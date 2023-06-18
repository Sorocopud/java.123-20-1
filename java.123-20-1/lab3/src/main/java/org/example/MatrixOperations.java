package org.example;

import java.util.Random;
import java.util.Scanner;

public class MatrixOperations {
    private static final int MAX_SIZE = 20;
    private static final int RANDOM_RANGE = 100;

    private int[][] matrix;
    private int width;
    private int height;

    public static void main(String[] args) {
        MatrixOperations program = new MatrixOperations();
        program.run();
    }

    public void run() {
        readMatrixSize();
        fillMatrix();
        printMatrix();
        calculateMinMaxAverage();
        calculateGeometricMean();
    }

    private void readMatrixSize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the width of the matrix (not exceeding " + MAX_SIZE + "): ");
        width = scanner.nextInt();

        System.out.print("Enter the height of the matrix (not exceeding " + MAX_SIZE + "): ");
        height = scanner.nextInt();

        scanner.nextLine(); // Skip empty line

        scanner.close();
    }

    private void fillMatrix() {
        matrix = new int[height][width];
        Random random = new Random();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose manual matrix input? (Y/N): ");
        String choice = "";

        if (scanner.hasNextLine()) {
            choice = scanner.nextLine();
        }

        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("Enter the matrix elements:");

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print("Element [" + i + "][" + j + "]: ");
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                    }
                }
            }
        } else {
            System.out.println("The matrix will be filled with random numbers.");

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = random.nextInt(RANDOM_RANGE);
                }
            }
        }

        scanner.close();
    }

    private void printMatrix() {
        System.out.println("Matrix:");

        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }

    private void calculateMinMaxAverage() {
        int min = matrix[0][0];
        int max = matrix[0][0];
        double sum = 0;

        for (int[] row : matrix) {
            for (int element : row) {
                if (element < min) {
                    min = element;
                }
                if (element > max) {
                    max = element;
                }
                sum += element;
            }
        }

        double average = sum / (width * height);

        System.out.println("Minimum element: " + min);
        System.out.println("Maximum element: " + max);
        System.out.println("Average: " + average);
    }

    private void calculateGeometricMean() {
        double product = 1;

        for (int[] row : matrix) {
            for (int element : row) {
                product *= element;
            }
        }

        double geometricMean = Math.pow(product, 1.0 / (width * height));

        System.out.println("Geometric mean: " + geometricMean);
    }
}
