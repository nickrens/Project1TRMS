package dev.rens.services;

import java.util.List;

import dev.rens.models.AdditionalInfo;
import dev.rens.repositories.AdditionalInfoRepositoryImpl;

public class AdditionalInfoService {

	public static AdditionalInfoRepositoryImpl inforepo = new AdditionalInfoRepositoryImpl();
	
	public AdditionalInfo createAdditionalInfo(AdditionalInfo info) {
		
		return inforepo.createAdditionalInfo(info);
	}
	
	public AdditionalInfo getAdditionalInfo(int id) {
		return inforepo.getAdditionalInfo(id);
	}
	
	public AdditionalInfo getLatestAdditonalInfo() {
		return inforepo.getLatestAdditionalInfo();
	}
	
	public List<AdditionalInfo> getAllAdditionaInfo(){
		return inforepo.getAllAdditionalInfo();
	}
	
	public List<AdditionalInfo> getAllAdditionaInfoForEmployee(int id){
		return inforepo.getAllAdditionalInfoForEmployee(id);
	}
	
	public AdditionalInfo updateAdditionalInfo(AdditionalInfo update) {
		return inforepo.updateAdditionalInfo(update);
	}
	
	public boolean deleteAdditionalInfo(int id) {
		return inforepo.deleteAdditionalInfo(id);
	}
	
	
}
