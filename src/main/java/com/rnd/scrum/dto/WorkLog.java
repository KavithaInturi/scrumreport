/**
 * 
 */
package com.rnd.scrum.dto;

/**
 * @author Sambaiah.Jillellamud
 *
 */
public class WorkLog {
	private String user;
	private String updateDateTime;
	private String spentInDays;

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime
	 *            the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the spentInDays
	 */
	public String getSpentInDays() {
		return spentInDays;
	}

	/**
	 * @param minutesSpent
	 *            the minutesSpent to set
	 */
	public void setSpentInDays(String spentInDays) {
		this.spentInDays = spentInDays;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkLog [user=" + user + ", updateDateTime=" + updateDateTime + ", spentInDays=" + spentInDays + "]";
	}
}
