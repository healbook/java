package Goi1;

import java.util.Scanner;

public class bai3 {
	public static int min(int n) {
			Scanner key = new Scanner(System.in);
			int[] mang = new int[n];
			System.out.println("Nhap cac phan tu cua mang: ");
			for (int i = 0; i < n; i++) {
				System.out.print("a[" + i + "] = ");
				mang[i] = key.nextInt();
			}
			int gtnn = mang[0];	
			for (int i = 0; i < n; i++) 	
				if (mang[i] < gtnn)
					gtnn = mang[i];	
			return gtnn;
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap so phan tu cua mang: ");
		int n = key.nextInt();	
		System.out.println("Gia tri nho nhat cua mang la :" + min(n));

	}

}
