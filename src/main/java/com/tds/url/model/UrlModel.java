package com.tds.url.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "url")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String shortened;
	private Long accessCount = 0L;
    private LocalDate dateFirstAccess;
    private Double dailyAccessAverage = 0.0;

    public UrlModel(String url, String shortened) {
    	this.url = url;
    	this.shortened = shortened;
    }
}
