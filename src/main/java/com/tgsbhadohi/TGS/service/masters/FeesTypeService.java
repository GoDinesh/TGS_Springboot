package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.FeesType;

public interface FeesTypeService {
	public List<FeesType> getAllFeesType();
	public List<FeesType> saveFeesType(FeesType feesType); 
	public List<FeesType> getAllActiveFeesType(Boolean status);
}
