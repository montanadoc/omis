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
package omis.assessment.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Assessment Note Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 15, 2018)
 *@since OMIS 3.0
 *
 */
public class AssessmentNoteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<AssessmentNoteItem> assessmentNoteItems =
			new ArrayList<AssessmentNoteItem>();
	
	/**
	 * Default constructor for Assessment Note Form. 
	 */
	public AssessmentNoteForm() {
	}

	/**
	 * Returns the assessmentNoteItems.
	 * @return assessmentNoteItems - List<AssessmentNoteItem>
	 */
	public List<AssessmentNoteItem> getAssessmentNoteItems() {
		return this.assessmentNoteItems;
	}

	/**
	 * Sets the assessmentNoteItems.
	 * @param assessmentNoteItems - List<AssessmentNoteItem>
	 */
	public void setAssessmentNoteItems(
			final List<AssessmentNoteItem> assessmentNoteItems) {
		this.assessmentNoteItems = assessmentNoteItems;
	}
	
	
}
