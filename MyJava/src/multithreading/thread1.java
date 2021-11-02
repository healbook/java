package multithreading;
//BAI TAP 1 ( CO LOI GIAI)//
class thread_1 extends Thread {
	public void run() {
		for(int i = 1;i<10;i+=2) {
			System.out.print(i + " ");
			try {
				Thread.sleep(100);
			}
			catch(Exception e) {
				
			}
		}
	}
}
	class thread_2 extends Thread {
		public void run() {
			for(int i = 2;i<=10;i+=2) {
				System.out.print(i + " ");
				try {
					Thread.sleep(100);
				}
				catch(Exception e) {
					
				}
			}
		}
	}
		
			
	



public class thread1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thread_1 t1 = new thread_1();
		thread_2 t2 = new thread_2();
		t1.start();
		t2.start();
	}

}
