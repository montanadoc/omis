package omis.hearing.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * HearingNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 27, 2016)
 *@since OMIS 3.0
 *
 */
public interface HearingNote extends Creatable, Updatable{
	
	/**
	 * Returns the hearing note's hearing
	 * @return hearing
	 */
	public Hearing getHearing();
	
	/**
	 * Sets the hearing note's hearing
	 * @param hearing
	 */
	public void setHearing(Hearing hearing);
	
	/**
	 * Returns the hearing note's description
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Sets the hearing note's description
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the hearing note's date
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Sets the hearing note's date
	 * @param date
	 */
	public void setDate(Date date);
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
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
	public boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode();
}
