package OOP;

import java.util.Scanner;

public class QLYNS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap so luong nhan vien:");
		int soluongnv = key.nextInt();
		Person[] nv = new Person[soluongnv];
		for(int i=0;i<=soluongnv-1;i++) {
			
			nv[i] = new Employee();
			nv[i].nhap(i);
		}
		for(int i = 0;i<=soluongnv-1;i++) {
			System.out.println(nv[i].toString());
		}
		
	}

}
