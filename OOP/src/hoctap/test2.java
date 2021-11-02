package hoctap;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String chuoi = " Java 1234   Java 1235";
		int dem = chuoi.split("Java").length -1;
		
		
			int vitri1 = chuoi.lastIndexOf("Java");
			String thaythe = chuoi.replaceAll("Java", "java");
		int dem2 = 0;
		for(int i=0;i<chuoi.length();i++) {
			if(Character.isDigit(chuoi.charAt(i))) {
				dem2++;
		}
		}
		System.out.println("So tu Java co trong chuoi : " + dem);
		System.out.println("Vi tri tu java cuoi cung trong chuoi: " + vitri1);
		System.out.println("Chuoi sau khi thay the:\n"+ thaythe);
		System.out.println("So ky tu la so co trong chuoi: " + dem2);
		}
	}


