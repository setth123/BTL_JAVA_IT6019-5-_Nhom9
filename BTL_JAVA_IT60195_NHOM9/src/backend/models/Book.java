package backend.models;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Book {
	private String maSach;
	private String tenSach;
	private String tieuDe;
	private String NXB;
	private Date nph;
	private String theLoai;
	private int sl;
	private double gia;
	public Book() {
	
	}
	public Book(String maSach, String tenSach, String tieuDe, String NXB, Date nph, String theLoai, int sl,
			double gia) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tieuDe = tieuDe;
		this.NXB = NXB;
		this.nph = nph;
		this.theLoai = theLoai;
		this.sl = sl;
		this.gia = gia;
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public String getNXB() {
		return NXB;
	}
	public void setNXB(String nXB) {
		NXB = nXB;
	}
	public Date getNph() {
		return nph;
	}
	public void setNph(Date nph) {
		this.nph = nph;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	@Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("| %-10s | %-30s | %-20s | %-10s | %-12s | %-10s | %6d | %10.2f |",
                maSach, tenSach, tieuDe, NXB, dateFormat.format(nph), theLoai, sl, gia);
    }
	
}
