package com.transaction;

import java.io.*;
import java.util.Scanner;

public class Transaksi {
    private String noReferensi;
    private String tanggalWaktu;
    private String status;
    private String jenisTransaksi;
    private double nominal;
    private String berita;
    private Rekening pengirim;
    private Rekening penerima;

    public Transaksi(String noReferensi, String tanggalWaktu, String jenisTransaksi, double nominal, String berita, Rekening pengirim, Rekening penerima) {
        this.noReferensi = noReferensi;
        this.tanggalWaktu = tanggalWaktu;
        this.jenisTransaksi = jenisTransaksi;
        this.nominal = nominal;
        this.berita = berita;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.status = "Pending";
    }

    public Transaksi() {}

    public void setNoReferensi(String noReferensi) { this.noReferensi = noReferensi; }
    public void setTanggalWaktu(String tanggalWaktu) { this.tanggalWaktu = tanggalWaktu; }
    public void setStatus(String status) { this.status = status; }
    public void setJenisTransaksi(String jenisTransaksi) { this.jenisTransaksi = jenisTransaksi; }
    public void setNominal(double nominal) { this.nominal = nominal; }
    public void setBerita(String berita) { this.berita = berita; }
    public void setPengirim(Rekening pengirim) { this.pengirim = pengirim; }
    public void setPenerima(Rekening penerima) { this.penerima = penerima; }

    public String getNoReferensi() { return noReferensi; }
    public String getTanggalWaktu() { return tanggalWaktu; }
    public String getStatus() { return status; }
    public String getJenisTransaksi() { return jenisTransaksi; }
    public double getNominal() { return nominal; }
    public String getBerita() { return berita; }
    public Rekening getPengirim() { return pengirim; }
    public Rekening getPenerima() { return penerima; }

    public boolean processTransfer() {
        double totalDeduction = nominal + pengirim.calcBiayaAdmin(nominal);
        if (pengirim.getSaldo() >= totalDeduction) {
            pengirim.setSaldo(pengirim.getSaldo() - totalDeduction);
            penerima.setSaldo(penerima.getSaldo() + nominal);
            this.status = "Transfer Berhasil";
            return true;
        } else {
            this.status = "Transfer Gagal - Saldo Tidak Cukup";
            return false;
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            writer.println("==========================================");
            writer.println("               BCA TRANSFERS             ");
            writer.println("==========================================");
            writer.println("Status          : " + status);
            writer.println("Waktu           : " + tanggalWaktu);
            writer.println(String.format("Amount          : %s %,.2f", penerima.getMataUang().split(" - ")[0], nominal));
            writer.println("------------------------------------------");
            writer.println("Nama Penerima   : " + penerima.getNamaPemilik());
            writer.println("Rekening Tujuan : " + penerima.getNoRekening());
            writer.println("Jenis Transaksi : " + jenisTransaksi);
            writer.println("Mata Uang Tujuan: " + penerima.getMataUang());
            writer.println("Dari Rekening   : " + pengirim.getNoRekening());
            writer.println("Mata Uang Asal  : " + pengirim.getMataUang());
            writer.println(String.format("Nominal Tujuan  : %s %,.2f", penerima.getMataUang().split(" - ")[0], nominal));
            writer.println("Berita          : " + (berita.isEmpty() ? "-" : berita));
            writer.println("No. Referensi   : " + noReferensi);
            writer.println("==========================================");
            System.out.println("Receipt saved successfully to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("\n--- READING RECEIPT FROM FILE ---");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}