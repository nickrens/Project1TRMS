package dev.rens.repositories;

import java.util.List;

import dev.rens.models.AdditionalInfo;

public interface AdditionalInfoRepository {

	AdditionalInfo createAdditionalInfo(AdditionalInfo info);
	AdditionalInfo getAdditionalInfo(int id);
	List<AdditionalInfo> getAllAdditionalInfo();
	
	List<AdditionalInfo> getAllAdditionalInfoForEmployee(int employee_id);
	
	AdditionalInfo updateAdditionalInfo(AdditionalInfo update);
	boolean deleteAdditionalInfo(int id);
	
}
