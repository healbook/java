package OOP;

import java.util.Scanner;

public class sinhvien {
	String masv;
	String tensv;
	double m1;
	double m2;
	double m3;
	public sinhvien(String masinhvien, String tensv, double m1, double m2, double m3) {
		this.masv = masinhvien;
		this.tensv = tensv;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		
	}
	public sinhvien() {
		
	}
	public double tinhdiemtb() {
		return (m1 + m2+ m3)/3;
	}
	public double tongdiem3mon() {
		return m1 + m2 + m3;
	}
	public void output() {
		System.out.println("Ma SV : " + masv +
							"\nTen sv: " + tensv +
							"\nDiem mon 1: " + m1 + 
							"\nDiem mon 2: " + m2 +
							"\nDiem mon 3: " + m3 + 
							"\nDiem trung binh: " + tinhdiemtb() +
							"\nTong diem 3 mon: " + tongdiem3mon());
		
	}
	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);
		System.out.print("nhap so luong sinh vien: ");
		int soluongsv = key.nextInt();
		sinhvien[] mangsinhvien = new sinhvien[soluongsv];
		for(int i = 0; i < soluongsv; i++) {
			System.out.println("Nhap thong tin sinh vien thu " + (i + 1));
			System.out.print("Nhap ma sinh vien thu" + (i+1) + ":");
			key.nextLine();
			String masv = key.nextLine();
			System.out.print("Nhap ten sinh vien thu" + (i + 1) + ":");
			String tensv = key.nextLine();
			System.out.print("nhap diem mon 1 cua sinh vien" + (i+1) + ":");
			double m1 = key.nextDouble();
			System.out.print("Nhap diem mon 2 cua sinh vien" + (i+1) + ":");
			double m2 = key.nextDouble();
			System.out.print("Nhap diem mon 3 cua sinh vien " + (i+1) + ":");
			double m3 = key.nextDouble();
			mangsinhvien[i] = new sinhvien(masv, tensv, m1, m2, m3);
		}
		for(int i = 0; i < soluongsv; i++ ) {
			mangsinhvien[i].output();
		}
		}

}

