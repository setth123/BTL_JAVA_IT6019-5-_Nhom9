package backend.models;

import java.time.LocalDate;

public class BorrowSlip {
    private String maPhieuMuon;
    private LocalDate ngayMuon;
    private String maTaiKhoan;

    public BorrowSlip() {
    }

    public BorrowSlip(String maPhieuMuon, LocalDate ngayMuon, String maTaiKhoan) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-30s | %-20s |",
                maPhieuMuon, ngayMuon, maTaiKhoan);
    }
}
