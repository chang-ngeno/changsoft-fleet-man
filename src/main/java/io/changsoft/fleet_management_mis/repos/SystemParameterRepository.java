package io.changsoft.fleet_management_mis.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.changsoft.fleet_management_mis.domain.SystemParameter;


public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {
	
	List<SystemParameter> getSystemParameterByParent(int parent);
	
}
