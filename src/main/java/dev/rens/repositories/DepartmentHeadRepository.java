package dev.rens.repositories;

import java.util.List;

import dev.rens.models.DepartmentHead;

public interface DepartmentHeadRepository {

	List<DepartmentHead> getAllDepartmentHeads();
	DepartmentHead findDepartmentHead(String department);
}
