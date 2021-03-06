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
package omis.paroleeligibility.domain.impl;

import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Appearance category implementation.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public class AppearanceCategoryImpl implements AppearanceCategory {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	/**
	 * Instantiates an implementation of appearance category.
	 */
	public AppearanceCategoryImpl() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AppearanceCategory)) {
			return false;
		}
		AppearanceCategory that = (AppearanceCategory) obj;
		if (this.getId() == null) {
			throw new IllegalStateException("Id required");
		}
		if (!this.getId().equals(that.getId())) {
			return false;
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}
		if (!this.getValid().equals(that.getValid())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getId() == null) {
			throw new IllegalStateException("Id required.");
		}
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getId().hashCode();
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getValid().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Id: %s, Name: %s, Valid: %s",
				this.getId(),
				this.getName(),
				this.getValid());
	}
	
}
