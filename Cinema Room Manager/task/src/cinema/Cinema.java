package cinema;

import java.util.Scanner;

public class Cinema {



    public static void showMenu(){
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static int[][] initCinema(){
        Scanner scanner = new Scanner(System.in);
        int rows;
        int seats;
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        int[][] cinema = new int[rows][seats];

        for (int[] row : cinema){
            for (int seat : row){
                seat = 0;
            }
        }

        return cinema;
    }

    public static void showTheSeats(int[][] cinema){
        int i;
        int j;
        int rows = cinema.length;
        int seats = cinema[0].length;

        System.out.printf("%nCinema:%n  ");

        for (j = 0; j < seats; j++){
            System.out.printf("%d ", j + 1);
        }

        System.out.printf("%n");

        for (i = 0; i < rows; i++) {
            System.out.printf("%d ", i + 1);

            for (j = 0; j < seats; j++){
                System.out.printf("%c ", cinema[i][j] == 1 ? 'B' : 'S');
            }

            System.out.printf("%n");
        }
    }

    public static void buyTicket(int[][] cinema) {
        Scanner scanner = new Scanner(System.in);
        int rows = cinema.length;
        int seats = cinema[0].length;

        System.out.printf("%n");
        System.out.printf("Enter a row number:%n");
        int row = scanner.nextInt();
        System.out.printf("Enter a seat number in that row:%n");
        int seat = scanner.nextInt();
        row--;
        seat--;

        while (row < 0 || row >= rows || seat < 0 || seat >= seats || cinema[row][seat] == 1) {
            if (row < 0 || row >= rows || seat < 0 || seat >= seats) {
                System.out.println("\nWrong input!");
            } else if (cinema[row][seat] == 1) {
                System.out.println("\nThat ticket has already been purchased!");
            }

            System.out.printf("%n");
            System.out.printf("Enter a row number:%n");
            row = scanner.nextInt();
            System.out.printf("Enter a seat number in that row:%n");
            seat = scanner.nextInt();
            row--;
            seat--;
        }

        cinema[row][seat] = 1;

        int price;

        if (rows * seats <= 60 || (row + 1) <= rows / 2) {
            price = 10;
        } else {
            price  = 8;
        }

        System.out.printf("Ticket price: $%d%n", price);
    }

    public static void statistics(int[][] cinema) {
        int tickets = 0;
        int income = 0;
        int total = 0;
        double percentage = 0;
        int row;
        int seat;
        int rows = cinema.length;
        int seats = cinema[0].length;

        //find tickets and income
        for (row = 0; row < rows; row++) {
            for (seat = 0; seat < seats; seat++) {
                if (cinema[row][seat] == 1) {
                    tickets++;

                    if (rows * seats <= 60 || (row + 1) <= rows / 2) {
                        income += 10;
                    } else {
                        income  += 8;
                    }
                }
            }
        }

        //find total income
        if (rows * seats <= 60) {
            total = rows * seats * 10;
        } else {
            total = rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8;
        }

        //find percentage
        percentage = (double) tickets / (rows * seats) * 100;

        System.out.printf("%n");
        System.out.printf("Number of purchased tickets: %d%n", tickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", income);
        System.out.printf("Total income: $%d%n", total);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] cinema;
        int option;

        cinema = initCinema();
        showMenu();
        option = scanner.nextInt();

        while (option != 0) {
            switch (option){
                case 1:
                    showTheSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema);
                    break;
                case 3:
                    statistics(cinema);
                    break;
                default:
                    break;
            }

            showMenu();
            option = scanner.nextInt();
        }
    }
}