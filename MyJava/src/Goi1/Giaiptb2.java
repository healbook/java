package Goi1;

import java.util.Scanner;

public class Giaiptb2 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		double a, b, c, delta;
		double x1, x2, x;
		Scanner phim = new Scanner(System.in);
		System.out.print("nhap a = ");
		a = phim.nextDouble();
		System.out.print("nhap b = ");
		b = phim.nextDouble();
		System.out.print("nhap c = ");
		c = phim.nextDouble();
		delta = b*b - 4*a*c;
		System.out.println("ta co delta = " + delta);
		if (a == 0)  
			if (b==0) 
				if (c==0) System.out.println("Phuong trinh co vo so nghiem");
				else System.out.println("Phuong trinh vo nghiem");
			else System.out.println("Phuong trinh co nghiem x = " + (-b/(2*a)) );
		else 
		{ if (delta > 0) {
			x1 = (-b + Math.sqrt(delta)/(2*a));
			x2 = (-b + Math.sqrt(delta)/(2*a));
			System.out.println("Phuong trinh co hai nghiem phan biet x1 = " + x1 + " va x2 "  + x2 );
		}
		else if (delta == 0) {
				x = -b/2*a;
			 	System.out.println("Phuong trinh co nghiem kep x = " + x);
		}
			 	else System.out.println("Phuong trinh vo nghiem");
				 
			}
		
		}
		
		
		
			
		
		
	}
	

