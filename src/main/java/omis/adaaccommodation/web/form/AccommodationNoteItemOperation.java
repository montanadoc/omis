package omis.adaaccommodation.web.form;

/**
 * Accommodation note item operation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0
 */
public enum AccommodationNoteItemOperation {
	
	/**
	 * Creates an ADA accommodation note.
	 */
	CREATE,
	
	/**
	 * Updates an ADA accommodation note.
	 */
	UPDATE,
	
	/**
	 * Removes an ADA accommodation note.
	 */
	REMOVE;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}
}