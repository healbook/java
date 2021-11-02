package QLSV;


public class Student implements Comparable<Student>  {
	private int id;
	private String ten;
	private int sohocphan;
	private Course[] dshp;
	private float aver;
	float tongsotc;
	public Student() {
		
	}
	public float getTongsotc() {
		return this.tongsotc;
	}
	public Student(int id,int sohocphan, String ten,Course[] dshp) {
		this.id=id;
		this.ten=ten;
		this.sohocphan=sohocphan;
		this.dshp=dshp;
		float tongsotc=0;
		float tongdiem=0;
		for(int i = 0;i<sohocphan;i++) {
			tongsotc+=this.dshp[i].getSotc();
			tongdiem+=this.dshp[i].getDiemhp()*this.dshp[i].getSotc();
		}
		this.aver = tongdiem/tongsotc;
	}
	public Student(int id, String ten, int tongsotc)
	{
		this.id = id;
		this.ten=ten;
		//aver=a;
		this.tongsotc=tongsotc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getSohocphan() {
		return sohocphan;
	}
	public void setSohocphan(int sohocphan) {
		this.sohocphan = sohocphan;
	}
	public Course[] getDshp() {
		return dshp;
	}
	public void setDshp(Course[] dshp) {
		this.dshp = dshp;
	}
	public float getAver() {
		return aver;
	}
	public void setAver(float aver) {
		this.aver = aver;
	}
	public float getDiemhp(int i)
	{
		return this.dshp[i].getDiemhp();
	}
	public String getTenhp(int i)
	{
		return this.dshp[i].getTenhp();
	}
	public float getSotc(int i)
	{
		return this.dshp[i].getSotc();
	}
	@Override
	public int compareTo(Student d) {
		return Float.compare((this.aver), (d.getAver()));
	}
	
	}
