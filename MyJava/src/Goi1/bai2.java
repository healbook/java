package Goi1;
import java.util.Arrays;
import java.util.Scanner;
public class bai2{
	public static void mang(int n) {
	Scanner key = new Scanner(System.in);
	int[] mang = new int[n];
	System.out.println("Nhap cac phan tu cua mang: ");
	for (int i = 0; i < n; i++) {
		System.out.print("a[" + i + "] = ");
		mang[i] = key.nextInt();
	}
		for (int i = 0; i < n; i++)
		for (int j = i + 1; j < n; j++)
			if (mang[i] < mang[j]) {
				int t = mang[i];
				mang[i] = mang[j];
				mang[j] = t;
				}
		System.out.println("Thu tu giam dan cua cac phan tu la :");
		for(int i = 0; i < n ; i++)
			System.out.print(mang[i] + " ");
	} 	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap so phan tu cua mang: ");
		int n  = key.nextInt();
		mang(n);
		
	}

}
