package backend.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import backend.utils.ReadData;
public class Book {
	private String maSach;
	private String tenSach;
	private String NXB;
	private LocalDate nph;
	private String theLoai;
	private int sl;
	private double gia;
	private boolean isAvaiable;
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
		this.isAvaiable=isBorrow;
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
		return isAvaiable;
	}
	public void setBorrow(boolean isBorrow) {
		this.isAvaiable = isBorrow;
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
	
	public static Book getBookByTitle(String maSach) {
    	String path=ReadData.f_path("/DemoDB/Book.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                //line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8 && parts[0].trim().equals(maSach)) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String releaseDate = parts[3].trim();
                    String category = parts[4].trim();
                    int quantity = Integer.parseInt(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());
                    return new Book(code, name, author, LocalDate.parse(releaseDate), category, quantity, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

	
}
