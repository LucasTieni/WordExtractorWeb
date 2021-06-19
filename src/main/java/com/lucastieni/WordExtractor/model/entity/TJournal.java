package com.lucastieni.WordExtractor.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table (name = "journal", schema = "WordExtractor")
@Builder
@Data
public class TJournal {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column (name = "Name")
	private String Name;
	
	@Column (name = "IssnP")
	private String IssnP;
	
	@Column (name = "IssnE")
	private String IssnE;
	
	@Column (name = "IdName")
	private String IdName;
	
	@Column (name = "AbbrevName")
	private String AbbrevName;
	
	@Column (name = "PublisherName")
	private String PublisherName;
}
