package omis.separationneed.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.separationneed.report.SeparationNeedProfileItemReportService;
import omis.separationneed.report.SeparationNeedProfileItemSummary;

/** Hibernate implementation of separation need profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 17, 2016)
 * @since OMIS 3.0 */
public class SeparationNeedProfileItemReportServiceHibernateImpl 
	implements SeparationNeedProfileItemReportService {
	private static final String 
		FIND_SEPARATION_NEED_PROFILE_ITEM_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME
		 = "findSeparationNeedProfileItemSummaryByOffenderAndDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	//private static final String EFFECTIVE_DATE_PARAM_NAME 
	//	= "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public SeparationNeedProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeedProfileItemSummary findSeparationNeedProfileItemSummaryByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_SEPARATION_NEED_PROFILE_ITEM_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		//q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((SeparationNeedProfileItemSummary) q.uniqueResult());
	}

}