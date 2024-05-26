package backend.models;
import java.time.LocalDate;
public class Book {
	private String maSach;
	private String tenSach;
	private String NXB;
	private LocalDate nph;
	private String theLoai;
	private int sl;
	private double gia;
	private boolean isBorrow;
	public Book() {
	
	}
	public Book(String maSach, String tenSach, String NXB, LocalDate nph, String theLoai, int sl,
			double gia,boolean isBorrow) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.NXB = NXB;
		this.nph = nph;
		this.theLoai = theLoai;
		this.sl = sl;
		this.gia = gia;
		this.isBorrow=isBorrow;
	}
	public Book(String maSach, String tenSach, String NXB, LocalDate nph, String theLoai, int sl,
			double gia) {
		this.maSach = maSach;
		this.tenSach = tenSach;
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
	public String getNXB() {
		return NXB;
	}
	public void setNXB(String nXB) {
		NXB = nXB;
	}
	public LocalDate getNph() {
		return nph;
	}
	public void setNph(LocalDate nph) {
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
	
	public boolean getBorrow() {
		return isBorrow;
	}
	public void setBorrow(boolean isBorrow) {
		this.isBorrow = isBorrow;
	}
	public void reduceQuantity(int amount) {
		if (this.sl >= amount) {
			this.sl -= amount;
		}
	}
	@Override
    public String toString() {
        return (this.getMaSach()+"|"+this.getTenSach()+"|"+this.getNXB()+"|"+this.getNph()+"|"+this.getTheLoai()+"|"+this.getSl()+"|"+this.getGia()+"|"+this.getBorrow());
    }
	
}
