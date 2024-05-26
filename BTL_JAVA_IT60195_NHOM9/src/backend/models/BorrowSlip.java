package backend.models;

import java.time.LocalDate;

public class BorrowSlip {
    private String maPhieuMuon;
    private LocalDate ngayMuon;
    private LocalDate ngayTra;
    private String maTaiKhoan;
    private Book sachMuon;
    private boolean trangThai;

    public BorrowSlip(String maPhieuMuon, LocalDate ngayMuon, String maTaiKhoan, Book sachMuon, boolean trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayMuon.plusDays(14);
        this.maTaiKhoan = maTaiKhoan;
        this.sachMuon = sachMuon;
        this.trangThai = trangThai;
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

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Book getSachMuon() {
        return sachMuon;
    }

    public void setSachMuon(Book sachMuon) {
        this.sachMuon = sachMuon;
    }

    @Override
    public String toString() {
        return String.format("| %-25s | %-25s | %-25s | %-25s | %-25s | %-25s |",
                maPhieuMuon, ngayMuon, ngayTra, maTaiKhoan, trangThai ? "Active" : "Inactive", sachMuon.getTenSach());
    }
}
