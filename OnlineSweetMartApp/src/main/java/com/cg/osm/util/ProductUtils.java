package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import com.cg.osm.entity.Product;
import com.cg.osm.model.ProductDTO;

public class ProductUtils {
	public static List<ProductDTO> convertToProductDtoList(List<Product> list){
		List<ProductDTO> dtolist = new ArrayList<ProductDTO>();
		for(Product product : list) 
			dtolist.add(convertToProductDto(product));
		return dtolist;
	}
	
	public static ProductDTO convertToProductDto(Product product) {
		ProductDTO productdto = new ProductDTO();
		productdto.setProductid(product.getProductid());
		productdto.setName(product.getName());
		productdto.setPrice(product.getPrice());
		productdto.setDescription(productdto.getDescription());
		productdto.setAvailable(productdto.isAvailable());  //boolean
		return productdto;
	}
	
	
	public static Product convertToProduct(ProductDTO productdto) {
		Product product = new Product();
		productdto.setProductid(product.getProductid());
		productdto.setName(product.getName());
		productdto.setPrice(product.getPrice());
		productdto.setDescription(productdto.getDescription());
		productdto.setAvailable(productdto.isAvailable());  //boolean
		return product;
	}	
	

}
