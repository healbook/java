package OOP;

	import java.util.Scanner;
	
	public class mang {
		public static void nhapinmang(int mang[], int n) {
			Scanner key = new Scanner(System.in);
			for(int i = 0;i<n;i++) {
				System.out.println("Mang[" + i + "] = ");
				mang[i] = key.nextInt();
			}
			System.out.println("Cac phan tu cua mang : ");
			for (int i = 0; i < mang.length; i++) {
				System.out.println(mang[i] + " ");
			}
	
		}
		public static int mang(int mang[], int n) {
			int sum = 0;
			for(int i=0;i<n;i++) {
				if(i%2==0) {
					if(mang[i] %2 !=0) {
						sum = sum + mang[i];
				}
			}
		} System.out.println("Tong so le o vi tri chan = " + sum);
			return sum;
	}
		public static boolean checksonguyento(int x) {
			if(x<2) {
				return false;
			}
			for(int i = 2;i<=x/2; i++) {
				if(x%i==0) {
					return false;
				}
			} return true;
	
		}
		public static int songuyento(int mang[], int n) {
			int sum = 0;
			for(int i = 0;i<n;i++) {
				if(checksonguyento(mang[i])==true) {
					sum = sum + mang[i];
				}
			} 
			System.out.println("Tong so nguyen to = " + sum);
			return sum;
		} 
		public static void sapxepmang(int mang[], int n) {
			int k=0;
			for (int i = 0;i<n;i++) 
				for(int j = i + 1;j<n ;j++) 
					if(mang[i]>mang[j]) {
						k = mang[i];
						mang[i] = mang[j];
						mang[j] = k;
					}
					for(int i = 0;i<n;i++) 
						System.out.print(" " + mang[i]);
				
		}
			
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Scanner key = new Scanner(System.in);
			System.out.println("Nhap n:");
			int n = key.nextInt();
			int mang[] = new int[n];
			nhapinmang(mang, n);
			mang(mang, n);
			songuyento(mang, n);
			System.out.println("Thu tu tang dan cua mang:");
			sapxepmang(mang, n);
		}
	
	}
