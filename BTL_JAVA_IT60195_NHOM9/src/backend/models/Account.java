package backend.models;

public class Account {
    private static int nextId = 1;
    private  String maTaiKhoan;
    private String tenNguoiDung;
    private String diaChi;
    private String soDienThoai;
    private String tenDangNhap;
    private String matKhau;
    private boolean isActive;

    public Account() {
    }

    public Account(String tenNguoiDung, String diaChi, String soDienThoai, String tenDangNhap, String matKhau,boolean isActive) {
        this.maTaiKhoan = "DG" + nextId++;
        this.tenNguoiDung = tenNguoiDung;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.isActive=isActive;
    }

    public Account(String maTaiKhoan, String tenNguoiDung, String diaChi, String soDienThoai, String tenDangNhap, String matKhau, boolean isActive) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenNguoiDung = tenNguoiDung;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.isActive=isActive;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
    public String toString() {
        return (maTaiKhoan+"|"+tenNguoiDung+"|"+diaChi+"|"+soDienThoai+"|"+tenDangNhap+"|"+matKhau+"|"+isActive);
    }
}
