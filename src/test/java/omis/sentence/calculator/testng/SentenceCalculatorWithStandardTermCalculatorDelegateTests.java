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
package omis.sentence.calculator.testng;

import java.util.Date;

import org.testng.annotations.Test;

import omis.sentence.calculator.SentenceCalculator;
import omis.term.domain.component.Term;

/**
 * Tests for term calculator.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (March 1, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"sentence", "calculator"})
public class SentenceCalculatorWithStandardTermCalculatorDelegateTests
		extends AbstractSentenceCalculatorWithStandardTermCalculatorDelegateTests {
	
	/** Tests prison term calculation with leap year. */
	public void testPrisonTermCalculationWithLeapYear() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(3, 0, 0))
				.withSentenceCommencementDate(this.parseDate("03/11/2005"))
				.build();
		Date actual = calculator.calculatePrisonDischargeDate();
		Date expected = this.parseDate("03/10/2008");
		assert actual.equals(expected) : String.format(
				"Prison discharge date not calculated correctly - %s expected;"
						+ " %s actual",
				this.formatDate(expected),
				this.formatDate(actual));
	}
	
	/** Tests prison term calculation with leap year. */
	public void testPrisonTermCalculationWithTwoLeapYears() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(5, 0, 0))
				.withSentenceCommencementDate(this.parseDate("01/06/2008"))
				.build();
		Date actual = calculator.calculatePrisonDischargeDate();
		Date expected = this.parseDate("01/04/2013");
		assert actual.equals(expected) : String.format(
				"Prison discharge date not calculated correctly - %s expected;"
						+ " %s actual",
				this.formatDate(expected),
				this.formatDate(actual));
	}
	
	/** Tests prison days calculation. */
	public void testPrisonDaysCalculation() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(3, 0, 0))
				.build();
		int actual = calculator.calculatePrisonDays();
		int expected = 1095;
		assert actual == expected : String.format(
				"Prison days not calculated correctly - %d expected; %d found",
				expected, actual);
	}
	
	/** Tests jail time calculation with single term. */
	public void testJailTimeCalculationWithSingleTerm() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withJailTerm(this.parseDate("03/15/2006"),
						this.parseDate("05/17/2006"))
				.build();
		int actual = calculator.getJailTime();
		int expected = 64;
		assert actual == expected : String.format(
				"Jail time not calculated correctly - %d expected; %d found",
				expected, actual);
	}
	
	/** Tests jail time calculation with multiple terms. */
	public void testJailTimeCalculationWithMultipleTerms() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withJailTerm(this.parseDate("09/06/2004"),
						this.parseDate("09/10/2004"))
				.withJailTerm(this.parseDate("02/16/2005"),
						this.parseDate("04/06/2005"))
				.build();
		int actual = calculator.getJailTime();
		int expected = 55;
		assert actual == expected : String.format(
				"Jail time not calculated correctly - %d expected; %d found",
				expected, actual);
	}
	
	/** Tests probation days calculation. */
	public void testProbationDaysCalculation() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withProbationTerm(new Term(2, 0, 0))
				.build();
		int actual = calculator.calculateProbationDays();
		int expected = 730;
		assert actual == expected : String.format(
			"Probation days not calculated correctly - %d expected; %d found",
			expected, actual);
	}
	
	/** Tests iteration over multiple sentence calculators. */
	public void testIterable() {
		
		// Uses terms to test correct calculator is reached in iteration
		Term firstTerm = new Term(5, 0, 0);
		Term secondTerm = new Term(10, 0, 0);
		Term thirdTerm = new Term(15, 0, 0);
		Iterable<SentenceCalculator> calculators
			= this.getBuilderFactory()
					.createBuilder().withPrisonTerm(firstTerm)
					.nextConsecutive().withPrisonTerm(secondTerm).nextConsecutive()
					.withPrisonTerm(thirdTerm)
					.iterable();
		
		// Asserts that correct terms are in correct place
		int counter = 0;
		for (SentenceCalculator calculator : calculators) {
			if (counter == 0) {
				assert calculator.getPrisonTerm().equals(firstTerm)
					: String.format("Wrong prison term - %s expected; %s found",
							firstTerm, calculator.getPrisonTerm());
			} else if (counter == 1) {
				assert calculator.getPrisonTerm().equals(secondTerm)
					: String.format("Wrong prison term - %s expected; %s found",
							secondTerm, calculator.getPrisonTerm());
			} else if (counter == 2) {
				assert calculator.getPrisonTerm().equals(thirdTerm)
					: String.format("Wrong prison term - %s expected; %s found",
							thirdTerm, calculator.getPrisonTerm());
			} else {
				throw new UnsupportedOperationException(String.format(
						"Counter value %d not supported", counter));
			}
			counter++;
		};
		
		// Asserts that all three terms where asserted
		int expectedCount = 3;
		assert counter == expectedCount
			: String.format("Wrong number of calculators counted - %d expected;"
					+ " %d found", expectedCount, counter);
	}
	
	/** Tests that properties are set correctly when set directly. */
	public void testProperties() {
		
		// Captures properties, creates calculator with them
		Date offenseDate = this.parseDate("01/22/1985");
		Date sentenceDate = this.parseDate("12/15/1986");
		Date turnSelfInDate = this.parseDate("01/25/1985");
		Term prisonTerm = new Term(9, 0, 0);
		Term probationTerm = new Term(21, 0, 0);
		Date sentenceCommencementDate = this.parseDate("12/31/1986");
		int jailTime = 234;
		int deadTime = 878;
		SentenceCalculator calculator = this.getBuilderFactory().createBuilder()
				.withOffenseDate(offenseDate)
				.withSentenceDate(sentenceDate)
				.withTurnSelfInDate(turnSelfInDate)
				.withPrisonTerm(prisonTerm)
				.withProbationTerm(probationTerm)
				.withSentenceCommencementDate(sentenceCommencementDate)
				.withJailTime(jailTime)
				.withDeadTime(deadTime)
				.build();
		
		// Asserts values were correctly set
		assert calculator.getOffenseDate().equals(offenseDate)
			: String.format("Wrong offense date - %s expected; %s found",
					offenseDate, calculator.getOffenseDate());
		assert calculator.getSentenceDate().equals(sentenceDate)
			: String.format("Wrong sentence date - %s expected; %s found",
					sentenceDate, calculator.getSentenceDate());
		assert calculator.getTurnSelfInDate().equals(turnSelfInDate)
			: String.format("Wrong turn self in date - %s expected, %s found",
					turnSelfInDate, calculator.getTurnSelfInDate());
		assert calculator.getPrisonTerm().equals(prisonTerm)
			: String.format("Wrong prison term - %s expected; %s found",
					prisonTerm, calculator.getPrisonTerm());
		assert calculator.getProbationTerm().equals(probationTerm)
			: String.format("Wrong probation term - %s expected; %s found",
					probationTerm, calculator.getProbationTerm());
		assert calculator.getSentenceCommencementDate()
				.equals(sentenceCommencementDate)
			: String.format(
					"Wrong sentence commencement date - %s expected; %s found",
					sentenceCommencementDate,
					calculator.getSentenceCommencementDate());
		assert calculator.getJailTime() == jailTime
			: String.format("Wrong jail time - %s expected; %s found",
					jailTime, calculator.getJailTime());
		assert calculator.getDeadTime() == deadTime
			: String.format("Wrong dead time - %s expected; %s found",
					deadTime, calculator.getDeadTime());
	}
	
	/**
	 * Tests that initial sentence is not concurrent or consecutive.
	 */
	public void testInitial() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder().build();
		assert calculator.isInitial() : "First sentence is not initial";
		assert !calculator.isConsecutive()
			: "First sentence cannot be consecutive";
		assert !calculator.isConcurrent()
			: "First sentence cannot be concurrent";
	}
	
	/**
	 * Tests that consecutive sentence is not initial or concurrent.
	 */
	public void testConsecutive() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder().nextConsecutive()
				.build().next();
		assert !calculator.isInitial()
			: "Consecutive sentence cannot be initial";
		assert calculator.isConsecutive() : "Sentence is not consecutive";
		assert !calculator.isConcurrent() : "Sentence cannot be concurrent";
	}
	
	/** Tests that concurrent sentence is not initial or consecutive. */
	public void testConcurrent() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder().nextConcurrent()
				.build().next();
		assert !calculator.isInitial()
			: "Concurrent sentence cannot be initial";
		assert !calculator.isConsecutive() : "Sentence cannot be consecutive";
		assert calculator.isConcurrent() : "Sentence is not concurrent";
	}
	
	/**
	 * Tests that {@code IllegalStateException} is thrown when trying to
	 * calculate probation discharge date when initial sentence is missing
	 * commencement date and turn self in date.
	 */
	@Test(expectedExceptions = {IllegalStateException.class})
	public void testIllegalStateExceptionOnProbationDischargeDateCalculationWithMissingDates() {
		this.getBuilderFactory().createBuilder()
			.build().calculateProbationDischargeDate();
	}
}