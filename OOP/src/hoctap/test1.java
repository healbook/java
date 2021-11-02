package hoctap;

import java.util.Scanner;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		double sum = 0;
		for(i=1;i<=100;i++) {
			sum = sum + Math.sqrt(i);
		}
		System.out.println("Tong S = " + sum);
	}

}
