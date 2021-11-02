package QLSV;

public class Course {
	String idhp;
	String tenhp;
	float sotc;
	float diemhp;
	
	public Course() {
		
	}
	public Course(String idhp,String tenhp,float sotc, float diemhp) {
		this.idhp=idhp;
		this.tenhp=tenhp;
		this.sotc=sotc;
		this.diemhp=diemhp;
		
	}
	

	public String getIdhp() {
		return idhp;
	}
	public void setIdhp(String idhp) {
		this.idhp = idhp;
	}
	public String getTenhp() {
		return tenhp;
	}
	public void setTenhp(String tenhp) {
		this.tenhp = tenhp;
	}
	public float getSotc() {
		return sotc;
	}
	public void setSotc(float sotc) {
		this.sotc = sotc;
	}
	public float getDiemhp() {
		return diemhp;
	}
	public void setDiemhp(float diemhp) {
		this.diemhp = diemhp;
	}
	public String toString() {
		return "ID:" + this.idhp + "\tTen hoc phan:" + this.tenhp + "\tSo tin chi:" + this.sotc + "\tDiem hoc phan:" + this.diemhp + "\n";
	}
	
}
