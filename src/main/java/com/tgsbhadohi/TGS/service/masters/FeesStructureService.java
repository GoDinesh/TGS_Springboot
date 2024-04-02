package com.tgsbhadohi.TGS.service.masters;

import java.util.List;

import com.tgsbhadohi.TGS.classes.ResponseModel;
import com.tgsbhadohi.TGS.entities.masters.FeesStructure;

public interface FeesStructureService {
	public List<FeesStructure> getAllFeesStructure();
	public ResponseModel saveFeesStructure(FeesStructure feesStructure);
	public List<FeesStructure> getFeeStructureById(FeesStructure feesStructure);
	public List<FeesStructure> getAllActiveFeesStructure(Boolean status);
}
