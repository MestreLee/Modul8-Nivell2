package com.video.application;

import java.util.List;

import com.video.domain.*;
import com.video.persistence.VideoRepository;

public class VideoController {
	
	private VideoRepository repository = new VideoRepository();
	
	public VideoController(){}
		
		public void createVideo(String url, String titol, String tag) throws Exception{		
			Video video = new Video(url, titol, tag);
			repository.addVideo(video);
		}
		public void createVideo(String url, String titol) throws Exception{		
			Video video = new Video(url, titol);
			repository.addVideo(video);
		}
		
		public void afegirTags(String nomVideo, String tag) {
			List<Video> lista = repository.getAllVideos();
			for(Video e: lista) {
				if (nomVideo.toLowerCase().equals(e.getNomVideo().toLowerCase())){
					try {
						e.addOneTag(tag);
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
			}	
		}
		
		
		public String getAllVideos() {
			
			String res = "";
			List<Video> lista = repository.getAllVideos();
			
			for(Video e: lista) {
				res = res + e + "\n" + "\n";
			}
			
			return res;
		}
}

