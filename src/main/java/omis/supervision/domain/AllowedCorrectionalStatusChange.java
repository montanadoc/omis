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
package omis.supervision.domain;

import java.io.Serializable;

/**
 * Allowed change of correctional status. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public interface AllowedCorrectionalStatusChange
		extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the action for which to allow change.
	 * 
	 * @param action action for which to allow change
	 */
	void setAction(PlacementTermChangeAction action);
	
	/**
	 * Returns the action for which to allow change.
	 * 
	 * @return action for which to allow change
	 */
	PlacementTermChangeAction getAction();
	
	/**
	 * Sets correctional status from which to allow change.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to allow
	 * change
	 */
	void setFromCorrectionalStatus(CorrectionalStatus fromCorrectionalStatus);
	
	/**
	 * Returns correctional status from which to allow change.
	 * 
	 * @return correctional status from which to allow change
	 */
	CorrectionalStatus getFromCorrectionalStatus();
	
	/**
	 * Sets correctional status to which to allow change.
	 * 
	 * @param toCorrectionalStatus correctional status to which to allow
	 * change
	 */
	void setToCorrectionalStatus(CorrectionalStatus toCorrectionalStatus);
	
	/**
	 * Returns correctional status to which to allow change.
	 * 
	 * @return correctional status to which to allow change
	 */
	CorrectionalStatus getToCorrectionalStatus();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
