package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;

import service.ReportService;
import entities.ReportPlace;

@Named("report1")
@Scope("session")
public class Report1 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	ReportService rs;
	
	List<ReportPlace> list;
	
	Date date1;
	Date date2;
	
	double sum;
	long number;
	
	private Integer activeTabIndex = 0;
	
	private final String dateRequired = "input the date here";
	
	//
	private PieChartModel pieModel1;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
	public void checkRole() throws IOException  {
		HttpSession session = Util.getSession();
		String position = (String) session.getAttribute("position");		
		if(position == null || !position.equals("Business analyst")) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			System.out.println("User with " + position + " tried to get reach analyst page (report1)");

			context.redirect("signIn.xhtml");
		}
	}
     

    
    private void createPieModels() {
        createPieModel1();

    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        if(list == null || list.size() == 0) {
        	pieModel1.set("No data to display", 1);
        }
        else {
	        for(ReportPlace r : list) {
	        	pieModel1.set(r.getName(),r.getNumber());
	        }
        }
        
        pieModel1.setTitle("Diagram");
        pieModel1.setLegendPosition("N");
    }
     
	//S
	
	
	public String getReport() {
		number = 0;
		sum = 0;
		
		Instant instant = Instant.ofEpochMilli(date1.getTime());
		LocalDate ld1 = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

		Instant instant2 = Instant.ofEpochMilli(date2.getTime());
		LocalDate ld2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();
		
		list = rs.getReportPlace(ld1, ld2);
		for(ReportPlace rp: list) {
			System.out.println(rp.getStringForPrint());
			sum+=rp.getSum();
			number+=rp.getNumber();
		}
		
		createPieModels();
		
		return "report";
	}

	
	public final void onTabChange(final TabChangeEvent event) {
        TabView tv = (TabView) event.getComponent();
        this.activeTabIndex = tv.getActiveIndex();
}

	public ReportService getRs() {
		return rs;
	}


	public void setRs(ReportService rs) {
		this.rs = rs;
	}


	public List<ReportPlace> getList() {
		return list;
	}


	public void setList(List<ReportPlace> list) {
		this.list = list;
	}

	public String getDateRequired() {
		return dateRequired;
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

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public Integer getActiveTabIndex() {
		return activeTabIndex;
	}

	public void setActiveTabIndex(Integer activeTabIndex) {
		this.activeTabIndex = activeTabIndex;
	}
	
}
