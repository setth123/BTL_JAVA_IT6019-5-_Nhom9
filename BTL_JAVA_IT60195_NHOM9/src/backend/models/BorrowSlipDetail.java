package backend.models;

import java.time.LocalDate;

public class BorrowSlipDetail {
    private String maSach;
    private String maPhieuMuon;
    private LocalDate ngayTraDuKien;
    private LocalDate ngayTraThucTe;
    private int soLuong;

    public BorrowSlipDetail() {
    }

    public BorrowSlipDetail(String maSach, String maPhieuMuon, LocalDate ngayTraDuKien, LocalDate ngayTraThucTe, int soLuong) {
        this.maSach = maSach;
        this.maPhieuMuon = maPhieuMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        this.ngayTraThucTe = ngayTraThucTe;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public LocalDate getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(LocalDate ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public LocalDate getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public void setNgayTraThucTe(LocalDate ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-30s | %-20s | %-10s | %-12s |",
                maSach, maPhieuMuon, ngayTraDuKien, ngayTraThucTe, soLuong);
    }
}
