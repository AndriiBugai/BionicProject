package entities;

import java.sql.Date;

public class ReportDate implements Comparable<ReportDate> {
	private long number;
	private double sum;
	private Date date;
	
	public ReportDate(){
		
	}
	
	public ReportDate(Date date,long number,double sum  ) {
		this.date = date;
		this.number = number;
		this.sum = sum;
	}
	
	public String getStringForPrint() {
		String word = date + " " + number + " " +sum; 
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(ReportDate o) {
		// TODO Auto-generated method stub
		if(date.before(o.date)) return -1;
		if(date.after(o.date)) return 1;
		return 0;
	}


}
