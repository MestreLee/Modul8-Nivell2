package com.video.domain;


import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Video {
	
	protected String URL;
	protected String titol;
	protected LocalDateTime date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	protected LocalDateTime dataCreacio;
	protected List<Tag> tags = new ArrayList<Tag>();
	
	EstatVideo estat = null;
	
	public Video() {} // Constructor buit, per si de cas
	
	public Video (String url, String titol, String tag) {
		
		this.URL = url;
		this.titol = titol;
		this.date = LocalDateTime.now();
		String text = date.format(formatter);
		this.dataCreacio = LocalDateTime.parse(text, formatter);
		try {
			this.addOneTag(tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Video (String url, String titol) {
		
		this.URL = url;
		this.titol = titol;
		this.date = LocalDateTime.now();
		String text = date.format(formatter);
		this.dataCreacio = LocalDateTime.parse(text, formatter);
	}
	
	public String getNomVideo() {
		
		return this.titol;
	}
	
	public void addOneTag(String tag) throws Exception {
		
		Tag etiqueta = new Tag(tag);
		this.tags.add(etiqueta);
	}

	@Override
	public String toString() {
		
		estat = comprovaEstat();
		
		return "Títol: " + titol + " URL: " + URL + "\n" + 
				"Data i hora de creació :" + dataCreacio + "\n" + 
				"Estat: " + estat + "\n" +
				"Etiquetes del video: " + tags;

	}

	public LocalDateTime getData() {
		
		return this.dataCreacio;
	}
	
	public EstatVideo comprovaEstat() {
		LocalDateTime dataActual = LocalDateTime.now();

		long diff = ChronoUnit.SECONDS.between(dataCreacio, dataActual);
		
		if(diff <= 45) {
			//System.out.println(diff);
			estat = EstatVideo.UPLOADING;
		}else if ((diff > 45) && (diff <= 70)) {
			//System.out.println(diff);
			estat = EstatVideo.VERIFYING;
		}else {
			//System.out.println(diff);
			estat = EstatVideo.PUBLIC;
		}
		
		return estat;
		
	}

}
