package com.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String outputFile = "receipt_output.txt";

        System.out.println("==========================================");
        System.out.println("       BCA MOBILE / CLI TRANSFER          ");
        System.out.println("==========================================");

        try {
            // 1. Input Sender Details
            System.out.println("\n--- SENDER DETAILS ---");
            System.out.print("Enter Sender Account No : ");
            String senderAccNo = console.nextLine();

            System.out.print("Enter Sender Name       : ");
            String senderName = console.nextLine();

            System.out.print("Enter Current Balance   : ");
            double senderBalance = Double.parseDouble(console.nextLine());

            String senderCurr = "IDR - Indonesian Rupiah";
            RekeningBank pengirim = new RekeningBank(senderAccNo, senderName, senderBalance, senderCurr, "BCA", "KCU Jakarta");

            // 2. Select Transfer Type & Recipient Input
            System.out.println("\n--- SELECT TRANSACTION TYPE ---");
            System.out.println("1. Transfer ke Rekening BCA");
            System.out.println("2. Transfer Virtual Account");
            System.out.print("Choose option (1/2): ");
            int choice = Integer.parseInt(console.nextLine());

            Rekening penerima;
            String transType;

            if (choice == 1) {
                transType = "Transfer ke Rekening BCA";
                System.out.println("\n--- RECIPIENT DETAILS (BANK) ---");
                System.out.print("Enter Recipient Account No : ");
                String targetAccNo = console.nextLine();

                System.out.print("Enter Recipient Name       : ");
                String targetName = console.nextLine();

                penerima = new RekeningBank(targetAccNo, targetName, 0.0, senderCurr, "BCA", "KCU Jakarta");
            } else {
                transType = "Transfer Virtual Account";
                System.out.println("\n--- RECIPIENT DETAILS (VIRTUAL ACCOUNT) ---");
                System.out.print("Enter Virtual Account No   : ");
                String targetAccNo = console.nextLine();

                System.out.print("Enter Merchant / Company   : ");
                String merchantName = console.nextLine();

                penerima = new VirtualAccount(targetAccNo, merchantName, 0.0, senderCurr, "88001", merchantName);
            }

            // 3. Transaction Input
            System.out.println("\n--- TRANSACTION DETAILS ---");
            System.out.print("Enter Nominal Amount : ");
            double amount = Double.parseDouble(console.nextLine());

            System.out.print("Enter Memo / Berita  : ");
            String memo = console.nextLine();

            // Automatic Reference Number & Timestamp
            String refNo = "REF-" + System.currentTimeMillis();
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));

            // 4. Instantiate and Execute
            Transaksi trx = new Transaksi(refNo, dateTime, transType, amount, memo, pengirim, penerima);

            System.out.println("\nProcessing transaction...");
            if (trx.processTransfer()) {
                System.out.println("Status: " + trx.getStatus());
                System.out.println("Updated Sender Balance: IDR " + pengirim.getSaldo());

                // Save receipt output to file and display
                trx.saveToFile(outputFile);
                trx.readFromFile(outputFile);
            } else {
                System.out.println("Status: " + trx.getStatus());
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input format.");
        } catch (Exception e) {
            System.out.println("Error processing input: " + e.getMessage());
        } finally {
            console.close();
        }
    }
}