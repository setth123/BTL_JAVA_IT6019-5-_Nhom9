package backend.models;

public class Violation {
    private static int vpNo = 1;
    private String maViPham;
    private String maPhieuMuon;
    private String maTaiKhoan;
    private String lyDo;
    private int soNgayViPham;
    private double soTienPhat;

    public Violation() {
    }

    public Violation(String maPhieuMuon, String maTaiKhoan, String lyDo, int soNgayViPham, double soTienPhat) {
        this.maViPham = "VP-" + vpNo;
        vpNo++;
        this.maPhieuMuon = maPhieuMuon;
        this.maTaiKhoan = maTaiKhoan;
        this.lyDo = lyDo;
        this.soNgayViPham = soNgayViPham;
        this.soTienPhat = soTienPhat;
    }

    public Violation(String maViPham, String maPhieuMuon, String maTaiKhoan, String lyDo, int soNgayViPham, double soTienPhat) {
        this.maViPham = maViPham;
        this.maPhieuMuon = maPhieuMuon;
        this.maTaiKhoan = maTaiKhoan;
        this.lyDo = lyDo;
        this.soNgayViPham = soNgayViPham;
        this.soTienPhat = soTienPhat;
    }



    public static int getVpNo() {
        return vpNo;
    }

    public static void setVpNo(int vpNo) {
        Violation.vpNo = vpNo;
    }

    public String getMaViPham() {
        return maViPham;
    }

    public void setMaViPham(String maViPham) {
        this.maViPham = maViPham;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public int getSoNgayViPham() {
        return soNgayViPham;
    }

    public void setSoNgayViPham(int soNgayViPham) {
        this.soNgayViPham = soNgayViPham;
    }

    public double getSoTienPhat() {
        return soTienPhat;
    }

    public void setSoTienPhat(double soTienPhat) {
        this.soTienPhat = soTienPhat;
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-10s | %-10s | %-10s | %-50s | %-5s | %-10s",
                vpNo, maViPham, maPhieuMuon, maTaiKhoan, lyDo, soNgayViPham, soTienPhat);
    }
}
