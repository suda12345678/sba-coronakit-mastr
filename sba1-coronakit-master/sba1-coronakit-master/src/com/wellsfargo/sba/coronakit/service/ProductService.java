package com.wellsfargo.sba.coronakit.service;

import java.util.List;

import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.ProductMaster;



public interface ProductService {
	
	ProductMaster validateAndAdd(ProductMaster product) throws CkException;

	ProductMaster validateAndSave(ProductMaster product) throws CkException;

	boolean deleteItem(int icode) throws CkException;

	ProductMaster getItemById(int icode) throws CkException;

	List<ProductMaster> getAllItems() throws CkException;

}
