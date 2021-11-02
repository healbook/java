package Goi1;

import java.util.Scanner;

public class timmax3 {
	public static int timmax(int a,int b,int c) {
		int max = a;
		if (b > max)	
			max = b;
			if ( c > max)
				max = c;
			return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.print("Nhap a = ");
		int a = key.nextInt();
		System.out.print("Nhap b = ");
		int b = key.nextInt();
		System.out.print("Nhap c = ");
		int c = key.nextInt();
		System.out.println("So lon nhat trong 3 so la " + timmax(a, b, c));
		
	}

}
