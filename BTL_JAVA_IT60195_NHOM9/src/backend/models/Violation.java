package backend.models;

public class Violation {
	private static int vpNo=1;
    private String maViPham;
    private String maSach;
    private String maTaiKhoan;
    private String loaiViPham;
    private int soNgayViPham;
    private double soTienPhat;

    public Violation() {
    }

    public Violation( String maSach, String maTaiKhoan, String loaiViPham, int soNgayViPham, double soTienPhat) {
        this.maViPham = "VP-"+vpNo;
        vpNo++;
        this.maSach = maSach;
        this.maTaiKhoan = maTaiKhoan;
        this.loaiViPham = loaiViPham;
        this.soNgayViPham = soNgayViPham;
        this.soTienPhat = soTienPhat;
    }

    public String getMaViPham() {
        return maViPham;
    }

    public void setMaViPham(String maViPham) {
        this.maViPham = maViPham;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getLoaiViPham() {
        return loaiViPham;
    }

    public void setLoaiViPham(String loaiViPham) {
        this.loaiViPham = loaiViPham;
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
        return String.format("| %-10s | %-30s | %-20s | %-10s | %-12s | %-10s |",
                maViPham, maSach, maTaiKhoan, loaiViPham, soNgayViPham, soTienPhat);
    }
}
