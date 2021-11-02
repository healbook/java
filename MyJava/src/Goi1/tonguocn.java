package Goi1;

import java.util.Scanner;

public class tonguocn {
	public static int tonguocn(int n) {
		int sum = 0;
		for(int i = 1; i <= n; i++) 
			if (n%i==0) 	
				sum = sum + i;
				return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.print("Nhap n = ");
		int n = key.nextInt();
		System.out.println("Tong uoc so cua " + n + " la " + tonguocn(n));

	}

}
