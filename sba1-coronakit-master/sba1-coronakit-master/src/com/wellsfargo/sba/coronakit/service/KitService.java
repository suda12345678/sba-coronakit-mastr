package com.wellsfargo.sba.coronakit.service;

import java.util.List;

import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.KitDetail;





public interface KitService {
	
	KitDetail validateAndAdd(KitDetail kitdetail) throws CkException;

	KitDetail validateAndSave(KitDetail kitdetail) throws CkException;

	boolean deleteItem(int icode) throws CkException;

	KitDetail getItemById(int icode) throws CkException;

	List<KitDetail> getAllItems() throws CkException;

}
