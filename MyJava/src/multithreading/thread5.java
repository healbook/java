package multithreading;
//BAI TAP 5 ( CO LOI GIAI)//
public class thread5 {
	
	
	FacThread t1 = new FacThread(2);
	SumThread t2 = new SumThread(3);
	SumPowThread t3 = new SumPowThread(2,1);
	
	public thread5() {
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			long s = t1.getResult() + t2.getResult() + t3.getResult();
			System.out.print("    \nKet qua = " + s);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new thread5();
	}
class FacThread extends Thread {
	long gt = 1;
	int n;
	public FacThread(int k) {
		n=k;
		
	}
	public void run() {
		for(int i = 2;i<=n;i++) {
			gt*=i;
		}
		System.out.print(" \nF1 = " +gt);
	}
	public long getResult() {
		return gt;
	}
}
class SumThread extends Thread {
	long s = 0;
	int n;
	public SumThread(int k) {
		n=k;
	}
	public void run() {
		for(int i = 1;i<=n;i++) {
			s+=i;
		}
		System.out.print("  \nF2 = " + s);
	}
	public long getResult() {
		return s;
	}
}
class SumPowThread extends Thread {
	long s = 0;
	int x,n;
	public SumPowThread(int y,int k) {
		x=y;
		n=k;
	}
	public void run() {
		for(int i = 1;i<=n;i++) {
			s+=Math.pow(x, i);
		}
		System.out.print("  \nF3 = " + s);
	}
	public long getResult() {
		return s;
	}
}
}
