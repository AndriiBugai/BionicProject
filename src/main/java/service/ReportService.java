package service;

import idao.ITicketDao;
import iservice.IReportService;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import entities.ReportDate;
import entities.ReportPlace;

@Named
public class ReportService implements IReportService {

		 @Inject
		private ITicketDao td;
		 
		public List<ReportPlace> getReportPlace(LocalDate ld1, LocalDate ld2) {
			return td.getReportByPlace(ld1, ld2);
		}
		
		public List<ReportDate> getReportDate(LocalDate ld1, LocalDate ld2) {
			return td.getReportByDate(ld1, ld2);
		}
	
}
