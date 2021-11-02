package Goi1;

import java.util.Scanner;

public class tinhtongn {
	public static int tongn(int n) {
		int sum = 0;
		for(int i = 1; i <= n; i++) 
			sum = sum + i;
			return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.print("Nhap n = ");
		int n = key.nextInt();
		System.out.println("Tong tu 1 toi " + n + " la " + tongn(n));
	}

}
