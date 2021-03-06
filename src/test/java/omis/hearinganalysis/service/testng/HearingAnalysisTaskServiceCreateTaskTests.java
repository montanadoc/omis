/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.hearinganalysis.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.service.HearingAnalysisTaskService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.task.domain.Task;
import omis.task.service.delegate.TaskDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create tasks.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskServiceCreateTaskTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;

	@Autowired
	@Qualifier("taskDelegate")
	private TaskDelegate taskDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("hearingAnalysisTaskService")
	private HearingAnalysisTaskService hearingAnalysisTaskService;

	/* Test methods. */

	/**
	 * Tests the creation of a new task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateTask() throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");

		// Action
		Task task = this.hearingAnalysisTaskService.createTask(controllerName, 
				methodName, description, sourceAccountUser, originationDate, 
				completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}

	/**
	 * Test that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		this.taskDelegate.create(controllerName, methodName, description, 
				sourceAccountUser, originationDate, completionDate);

		// Action
		this.hearingAnalysisTaskService.createTask(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}		
}