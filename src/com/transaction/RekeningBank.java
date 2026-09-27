package com.transaction;

public class RekeningBank extends Rekening {
    private String namaBank;
    private String cabang;

    public RekeningBank(String noRekening, String namaPemilik, double saldo, String mataUang, String namaBank, String cabang) {
        super(noRekening, namaPemilik, saldo, mataUang);
        this.namaBank = namaBank;
        this.cabang = cabang;
    }

    public RekeningBank() {
        super();
    }

    public void setNamaBank(String namaBank) { this.namaBank = namaBank; }
    public void setCabang(String cabang) { this.cabang = cabang; }

    public String getNamaBank() { return namaBank; }
    public String getCabang() { return cabang; }

    @Override
    public double calcBiayaAdmin(double nominal) {
        return 0.0; // Same bank transfer fee
    }
}