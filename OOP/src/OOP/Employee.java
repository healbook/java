package OOP;

public class Employee extends Person {
	private int luong;
	private int danhgia;
	
	public Employee() {
		super();
	}
	public Employee(String ten,String tuoi,String diachi, int luong, int danhgia) {
		super();
		this.luong = luong;
		this.danhgia = danhgia;
	}
	
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
		this.luong = luong;
	}
	public int getDanhgia() {
		return danhgia;
	}
	public void setDanhgia(int danhgia) {
		this.danhgia = danhgia;
	}
	public int DanhgiaLuong() {
		return luong*danhgia;
	}
	@Override 
	public void nhap(int i) {
		super.nhap(i);
		System.out.println("Nhap luong nhan vien thu " +(i+1));
		luong = key.nextInt();
		System.out.println("Nhap danh gia luong nhan vien thu " +(i+1));
		 danhgia = key.nextInt();
	}
	@Override
	public String toString() {
		return super.toString() + "\nDanh gia luong:" + this.DanhgiaLuong();
		
	}
}
