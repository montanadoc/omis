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
package omis.earlyreleasetracking.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.document.domain.Document;

/**
 * Early Release Request Document Association.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 7, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestDocumentAssociation
		extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Early Release Request Document Association.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Early Release Request Document Association.
	 * @param id - ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the Early Release Request for the Early Release Request
	 * Document Association.
	 * @return earlyReleaseRequest - Early Release Request
	 */
	EarlyReleaseRequest getEarlyReleaseRequest();
	
	/**
	 * Sets the Early Release Request for the Early Release Request
	 * Document Association.
	 * @param earlyReleaseRequest - Early Release Request
	 */
	void setEarlyReleaseRequest(EarlyReleaseRequest earlyReleaseRequest);
	
	/**
	 * Returns the Document for the Early Release Request Document Association.
	 * @return document - Document
	 */
	Document getDocument();
	
	/**
	 * Sets the Document for the Early Release Request Document Association.
	 * @param document - Document
	 */
	void setDocument(Document document);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
