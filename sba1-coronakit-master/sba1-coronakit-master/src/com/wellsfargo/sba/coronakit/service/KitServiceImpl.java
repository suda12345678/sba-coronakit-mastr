package com.wellsfargo.sba.coronakit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.sba.coronakit.dao.KitDao;
import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.KitDetail;



public class KitServiceImpl implements KitService{
	
	KitDao kitDao;

	public KitServiceImpl() {
		kitDao = new KitDao();
	}

	private boolean isKitIdValid(Integer id) {
		return id > 0;
	}

	private boolean isCoronaKitIdVaid(Integer cornakitid) {
		return cornakitid > 0;
	}

	private boolean isProductIdValid(Integer prodid) {
		return prodid >= 0;
	}
	private boolean isAmountValid(Double amount) {
		return amount > 0;
	}
	private boolean isQuantityValid(Integer qunatity) {
		return qunatity >= 0;
	}

	private boolean isValidKitDetail(KitDetail kitdetail) throws CkException {
		List<String> errMsg = new ArrayList<String>();

		boolean isValid = true;

		if (!isKitIdValid(kitdetail.getId())) {
			isValid = false;
			errMsg.add("id can not be null or negative or zero");
		}

		if (!isCoronaKitIdVaid(kitdetail.getCoronaKitId())) {
			isValid = false;
			errMsg.add("Corona kit id can not be null or negative or zero");
		}

		if (!isProductIdValid(kitdetail.getProductId())) {
			isValid = false;
			errMsg.add("Product id can not be null or negative or zero");
		}

		if (!isQuantityValid(kitdetail.getQuantity())) {
			isValid = false;
			errMsg.add("Amount can not be null and shoul ");
		}

		if (isAmountValid(kitdetail.getAmount())) {
			isValid = false;
			errMsg.add("Amount can not be null and shoul ");
		}

		if (!isValid) {
			throw new CkException(errMsg.toString());
		}

		return isValid;
	}

	public KitDetail validateAndAdd(KitDetail kitdetail) throws CkException {
		if(kitdetail!=null) {
			if(isValidKitDetail(kitdetail)) {
				kitDao.add(kitdetail);
			}
		}
		return kitdetail;
	}

	public KitDetail validateAndSave(KitDetail kitdetail) throws CkException {
		if(kitdetail!=null) {
			if(isValidKitDetail(kitdetail)) {
				kitDao.save(kitdetail);
			}
		}
		return kitdetail;
	}

	public boolean deleteItem(int kitid) throws CkException {
		return kitDao.deleteById(kitid);
	}

	public KitDetail getItemById(int kitid) throws CkException {
		return kitDao.getById(kitid);
	}

	public List<KitDetail> getAllItems() throws CkException {
		return kitDao.getAll();
	}

}
