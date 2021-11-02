package OOP;

import java.util.Scanner;

public class Person {
	Scanner key = new Scanner(System.in);
	private String ten;
	private String tuoi;
	private String diachi;
	public Person() {
		super();
	}
	public Person(String ten, String tuoi, String diachi) {
		super();
		this.ten = ten;
		this.tuoi = tuoi;
		this.diachi = diachi;
	}
	

	
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getTuoi() {
		return tuoi;
	}
	public void setTuoi(String tuoi) {
		this.tuoi = tuoi;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public int DanhgiaLuong() {
		return 0;
	}
	public void nhap(int i) {
		System.out.println("Nhap ten nhan vien thu " +(i+1));
		ten = key.nextLine();
		System.out.println("Nhap tuoi nhan vien thu " +(i+1));
		tuoi = key.nextLine();
		System.out.println("Nhap dia chi nhan vien thu " +(i+1));
		diachi = key.nextLine();
	}
	@Override
	public String toString() {
		return "Thong tin nhan vien: \nTen nhan vien: " + this.ten + "\nTuoi: " + this.tuoi
				+ "\nDia chi:  " + this.diachi;
	}
		
}