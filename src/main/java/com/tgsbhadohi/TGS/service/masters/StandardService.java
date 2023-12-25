package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.entities.masters.Standard;

public interface StandardService {
	public List<Standard> getAllStandard();
	public List<Standard> getStandardById(Long id);
	public List<Standard> saveStandard(Standard standard);
	public List<Standard> getAllActiveStandard(Boolean status);
}
