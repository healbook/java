package Goi1;

public class tongsole {
	public static int tongsole() {
		int sum = 0;
		for(int i = 1; i<= 100; i++)
			if (i%2!=0)
				sum = sum + i;
		return sum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Tong cac so le tu 1 den 100 la " + tongsole());
	}

}
