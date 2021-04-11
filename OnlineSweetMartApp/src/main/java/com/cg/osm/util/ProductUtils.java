package com.cg.osm.util;

import java.util.ArrayList;
import java.util.List;

import com.cg.osm.entity.Product;
import com.cg.osm.model.ProductDTO;

public class ProductUtils {
	public static List<ProductDTO> convertToProductDtoList(List<Product> list) {
		List<ProductDTO> dtolist = new ArrayList<ProductDTO>();
		for (Product product : list)
			dtolist.add(convertToProductDto(product));
		return dtolist;
	}

	public static ProductDTO convertToProductDto(Product product) {
		ProductDTO productdto = new ProductDTO();
		productdto.setProductId(product.getProductId());
		productdto.setName(product.getName());
		productdto.setPrice(product.getPrice());
		productdto.setPhotopath(product.getPhotopath());
		productdto.setDescription(product.getDescription());
		productdto.setAvailable(product.isAvailable());
		productdto.setCategory(product.getCategory());
		return productdto;
	}

	public static List<Product> convertToProductList(List<ProductDTO> dtolist) {
		List<Product> list = new ArrayList<Product>();
		for (ProductDTO productDto : dtolist)
			list.add(convertToProduct(productDto));
		return list;
	}

	public static Product convertToProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductId(productDTO.getProductId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setPhotopath(productDTO.getPhotopath());
		product.setDescription(productDTO.getDescription());
		product.setAvailable(productDTO.isAvailable());
		product.setCategory(productDTO.getCategory());
		return product;
	}

}
