package com.cts.memberService.dto;

import java.sql.Date;

public class BillsDto {

	private Date dueDate;
	private Date lastPaidDate;
	private int lateCharge;
	private int dueAmount;

	public BillsDto(Date dueDate, Date lastPaidDate, int lateCharge, int dueAmount) {
		super();
		this.dueDate = dueDate;
		this.lastPaidDate = lastPaidDate;
		this.lateCharge = lateCharge;
		this.dueAmount = dueAmount;
	}

	public int getLateCharge() {
		return lateCharge;
	}

	public void setLateCharge(int lateCharge) {
		this.lateCharge = lateCharge;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getLastPaidDate() {
		return lastPaidDate;
	}

	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}

	public int getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}

	public BillsDto() {

	}

	public BillsDto(Date dueDate, Date lastPaidDate, int dueAmount) {
		super();
		this.dueDate = dueDate;
		this.lastPaidDate = lastPaidDate;
		this.dueAmount = dueAmount;
	}

}
