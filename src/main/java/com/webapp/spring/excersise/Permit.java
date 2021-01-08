package com.webapp.spring.excersise;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "permits")
public class Permit {
	@Id
	@Column(name = "permit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int permitId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "from_date")
	private Date fromDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "to_date")
	private Date todate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "application_request_date")
	private Date applicationReqestDate;
	@Column(name = "director_id")
	private int directorId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "confirmation_date")
	private Date confirmationDate;
	String status;
	@Column(name = "permit_category")
	String permitCategory;
	String description;

}
