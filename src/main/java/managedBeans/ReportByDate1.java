package managedBeans;

import iservice.IReportService;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;

import service.ReportService;
import entities.ReportDate;


@Named("report2")
@Scope("session")
public class ReportByDate1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	IReportService rs;
	
	List<ReportDate> list;
	
	Date date1;
	Date date2;
	
	double sum;
	long number;
	
	////
	private LineChartModel dateModel;
	
	@PostConstruct
    public void init() {
        createDateModel();
    }
	
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Business analyst")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			context.redirect("signIn.xhtml");
		}
	}
 
    public LineChartModel getDateModel() {
        return dateModel;
    }
     
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        DateAxis axis = new DateAxis("Dates");  
        
       if(list == null || list.size() == 0) {

         dateModel.addSeries(series1);
         dateModel.setTitle("Tickets Sold");
         dateModel.setZoom(true);
         dateModel.getAxis(AxisType.Y).setLabel("Tickets Sold");
        
         axis.setTickAngle(-50);
         axis.setTickFormat("%b %#d, %y");
       }
       else {
       		Collections.sort(list);
       		

       		for(int i=0; i<list.size(); i++) {
       			ReportDate r = list.get(i);
       
       			
       		// dealing with befordate
       			Calendar cal2 = new GregorianCalendar();
       	        cal2.setTime(r.getDate());
       			cal2.add(Calendar.DATE, -1);
       			java.sql.Date sqlYesterday = new java.sql.Date(cal2.getTimeInMillis());
       			if(i-1 >= 0  && !list.get(i-1).equals(sqlYesterday)) {
       				
       				series1.set(sqlYesterday.toString() , 0 );
       			}
       			      			
       		// dealing with afterdate
       			Calendar cal = new GregorianCalendar();
       	        cal.setTime(r.getDate());
       			cal.add(Calendar.DATE, 1);
       			java.sql.Date sqlTomorrow = new java.sql.Date(cal.getTimeInMillis());
       	        
       	        
       			if(i+1<list.size()  && !list.get(i+1).equals(sqlTomorrow)) {
       				
       				series1.set(sqlTomorrow.toString() , 0 );
       			}
       			
   	 
       			
          }
       		
       		
       		for(ReportDate r: list) {
       		 series1.set(r.getDate().toString() , r.getNumber());
       		}
           dateModel.addSeries(series1);
           dateModel.setTitle("Tickets Sold");
           dateModel.setZoom(true);
           dateModel.getAxis(AxisType.Y).setLabel("Tickets Sold");
          
           axis.setTickAngle(-50);
           axis.setTickFormat("%b %#d, %y");
       }
 
        
 

         
        dateModel.getAxes().put(AxisType.X, axis);
    }
	
	////
	
	////
	
	
	
	private final String dateRequired = "input the date here";
	
	public String getReport() {
		number = 0;
		sum = 0;

		Instant instant = Instant.ofEpochMilli(date1.getTime());
		LocalDate ld1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

		Instant instant2 = Instant.ofEpochMilli(date2.getTime());
		LocalDate ld2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();
		
		list = rs. getReportDate(ld1, ld2);
		for(ReportDate rp: list) {
			System.out.println(rp.getStringForPrint());
			sum+=rp.getSum();
			number+=rp.getNumber();
		}
		
		createDateModel();
		
		return "report";
	}

	public IReportService getRs() {
		return rs;
	}

	public void setRs(ReportService rs) {
		this.rs = rs;
	}

	public List<ReportDate> getList() {
		return list;
	}

	public void setList(List<ReportDate> list) {
		this.list = list;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getDateRequired() {
		return dateRequired;
	}
}
