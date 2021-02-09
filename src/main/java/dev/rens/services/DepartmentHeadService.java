package dev.rens.services;

import java.util.List;

import dev.rens.models.DepartmentHead;
import dev.rens.repositories.DepartmentHeadRepositoryImpl;

public class DepartmentHeadService {

	public DepartmentHeadRepositoryImpl dhrepo = new DepartmentHeadRepositoryImpl();
	
	public List<DepartmentHead> getAllDepartmentHeads(){
		return dhrepo.getAllDepartmentHeads();
	}
	public DepartmentHead findDepartmentHead(String department) {
		return dhrepo.findDepartmentHead(department);
	}
}
