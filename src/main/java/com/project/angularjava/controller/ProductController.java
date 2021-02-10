package com.project.angularjava.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.project.angularjava.model.Products;
import com.project.angularjava.repository.ProductRepository;

@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
@RestController
public class ProductController {

	@Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/prod")
    public Iterable<Products> product() {
        return productRepository.findAll();
    }
    
	//@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String  helloSecured() {	
		System.out.println("calling");

		return "Secured Hello";
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value="/prod")
	public String save (@RequestBody Products product) {
		System.out.println("Inside"+product.getProdName());
		productRepository.save(product);
		return product.getId();
		
	}
	

	@RequestMapping(method = RequestMethod.PUT, value="/prod/{id}")
	public Products update (@PathVariable String id, @RequestBody Products product) {
		Optional<Products> pro = productRepository.findById(id);
		Products prod = pro.get();
		System.out.println(prod.getId()+"id");
		System.out.println(prod.getProdName()+"id");
		System.out.println(prod.getProdDesc()+"id");
		if (product.getProdName() !=null) {
			prod.setProdName(product.getProdName());
		} if (product.getProdDesc() !=null) {
			prod.setProdDesc(product.getProdDesc());
		}  if (product.getProdPrice() !=null) {
			prod.setProdPrice(product.getProdPrice());
		}  if (product.getProdImage() !=null) {
			prod.setProdImage(product.getProdImage());
		}

		
		 productRepository.save(prod);	
		return prod;
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/prod/{id}")
	public String delete (@PathVariable String id) {
		productRepository.deleteById(id);
		return id+" is deleted.";
		
	}
	
}