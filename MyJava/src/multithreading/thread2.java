package multithreading;
//BAI TAP 2 ( CO LOI GIAI)//
class mythread extends Thread {
	int start;
	public mythread(int s) {
		start=s;
	}
	public static synchronized void go(int s) {
		for(int i=s;i<=10;i+=2) {
			System.out.print(i + " ");
			try {
				Thread.sleep(100);
			}
			catch(Exception e ) {
				
			}
		}
	}
	public void run() {
		go(start);
	}
}




public class thread2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new mythread(1);
		Thread t2 = new mythread(2);
		t1.start();
		t2.start();
	}

}
