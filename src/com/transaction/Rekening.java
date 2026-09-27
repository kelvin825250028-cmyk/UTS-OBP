package com.transaction;

public abstract class Rekening {
    private String noRekening;
    private String namaPemilik;
    private double saldo;
    private String mataUang;

    public Rekening(String noRekening, String namaPemilik, double saldo, String mataUang) {
        this.noRekening = noRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
        this.mataUang = mataUang;
    }

    public Rekening() {}

    public void setNoRekening(String noRekening) { this.noRekening = noRekening; }
    public void setNamaPemilik(String namaPemilik) { this.namaPemilik = namaPemilik; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public void setMataUang(String mataUang) { this.mataUang = mataUang; }

    public String getNoRekening() { return noRekening; }
    public String getNamaPemilik() { return namaPemilik; }
    public double getSaldo() { return saldo; }
    public String getMataUang() { return mataUang; }

    public abstract double calcBiayaAdmin(double nominal);
}