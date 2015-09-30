package iservice;

import java.time.LocalDate;
import java.util.List;

import entities.ReportDate;
import entities.ReportPlace;

public interface IReportService {

	public List<ReportPlace> getReportPlace(LocalDate ld1, LocalDate ld2);
	
	public List<ReportDate> getReportDate(LocalDate ld1, LocalDate ld2);
	
}
