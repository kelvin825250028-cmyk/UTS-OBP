package com.transaction;

public class VirtualAccount extends Rekening {
    private String kodeCompany;
    private String merchantName;

    public VirtualAccount(String noRekening, String namaPemilik, double saldo, String mataUang, String kodeCompany, String merchantName) {
        super(noRekening, namaPemilik, saldo, mataUang);
        this.kodeCompany = kodeCompany;
        this.merchantName = merchantName;
    }

    public VirtualAccount() {
        super();
    }

    public void setKodeCompany(String kodeCompany) { this.kodeCompany = kodeCompany; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }

    public String getKodeCompany() { return kodeCompany; }
    public String getMerchantName() { return merchantName; }

    @Override
    public double calcBiayaAdmin(double nominal) {
        return 1000.0; // Fixed VA administrative fee
    }
}