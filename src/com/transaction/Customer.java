package com.transaction;

public class Customer extends Person implements Login {
    private String alamat;
    private String username;
    private String password;

    public Customer(String kode, String nama, String noHP, String alamat, String username, String password) {
        super(kode, nama, noHP);
        this.alamat = alamat;
        this.username = username;
        this.password = password;
    }

    public Customer() {
        super();
    }

    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public String getAlamat() { return alamat; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public void signUp() {
        System.out.println("User " + username + " registered successfully.");
    }

    @Override
    public void signIn() {
        System.out.println("User " + username + " logged in.");
    }
}