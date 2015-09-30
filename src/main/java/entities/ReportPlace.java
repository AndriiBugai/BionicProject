package entities;

public class ReportPlace {
	private long number;
	private double sum;
	private String name;
	
	public ReportPlace(){
		
	}
	
	public ReportPlace(String name,long number,double sum  ) {
		this.name = name;
		this.number = number;
		this.sum = sum;
	}
	
	public String getStringForPrint() {
		String word = name + " " + number + " " +sum; 
		return word;
	}
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
