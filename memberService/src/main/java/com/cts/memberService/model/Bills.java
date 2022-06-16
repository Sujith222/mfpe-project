package com.cts.memberService.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "Bills")

public class Bills {
	
	@Id
    @Column(name="MID")
	private String memberId;
	
//    @Column(name="PID")	
//	private String policyId;
    
    @Column(name="LastPaidDate")	
	private Date  lastPaidDate;
	@Column(name = "LateCharge")	
	private int  lateCharge;
    @Column(name="DueAmount")	
	private int dueAmount;
    
    @Column(name="DueDate")	
	private Date  dueDate;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

//	public String getPolicyId() {
//		return policyId;
//	}
//
//	public void setPolicyId(String policyId) {
//		this.policyId = policyId;
//	}

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

	public Bills(
			String memberId/* , String policyId */, Date lastPaidDate, int dueAmount, int lateCharge, Date dueDate) {
		super();
		this.memberId = memberId;
//		this.policyId = policyId;
		this.lastPaidDate = lastPaidDate;
		this.dueAmount = dueAmount;
		this.lateCharge = lateCharge;
		this.dueDate = dueDate;
	}

	public Bills() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}