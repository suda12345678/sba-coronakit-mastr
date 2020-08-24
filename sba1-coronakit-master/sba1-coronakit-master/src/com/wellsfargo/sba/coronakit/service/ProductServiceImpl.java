package com.wellsfargo.sba.coronakit.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.sba.coronakit.dao.ProductMasterDao;
import com.wellsfargo.sba.coronakit.exception.CkException;
import com.wellsfargo.sba.coronakit.modal.ProductMaster;



public class ProductServiceImpl implements ProductService{
	
	ProductMasterDao productDao;

	public ProductServiceImpl() {
		productDao = new ProductMasterDao();
	}

	
	private boolean isProductName(String name) {
		return name != null;
	}

	private boolean isCostValid(Double cost) {
		return cost >= 0;
	}
	private boolean isProductDesc(String proddesc) {

		return proddesc != null;
	}

	private boolean isValidProduct(ProductMaster product) throws CkException {
		List<String> errMsg = new ArrayList<String>();

		boolean isValid = true;

		
		if (!isProductName(product.getProductName())) {
			isValid = false;
			errMsg.add("Product Name can not be blank");
		}

		if (!isCostValid(product.getCost())) {
			isValid = false;
			errMsg.add("Cost can not less than 0");
		}

		if (!isProductDesc(product.getProductDescription())) {
			isValid = false;
			errMsg.add("product Desc can not be null");
		}

		
		if (!isValid) {
			throw new CkException(errMsg.toString());
		}
		System.out.println("isValid"+isValid);

		return isValid;
	}

	public ProductMaster validateAndAdd(ProductMaster product) throws CkException {
		if(product!=null) {
			if(isValidProduct(product)) {
				productDao.add(product);
			}
		}
		return product;
	}

	public ProductMaster validateAndSave(ProductMaster product) throws CkException {
		if(product!=null) {
			if(isValidProduct(product)) {
				productDao.save(product);
			}
		}
		return product;
	}

	public boolean deleteItem(int id) throws CkException {
		return productDao.deleteById(id);
	}

	public ProductMaster getItemById(int id) throws CkException {
		return productDao.getById(id);
	}

	public List<ProductMaster> getAllItems() throws CkException {
		return productDao.getAll();
	}

}
