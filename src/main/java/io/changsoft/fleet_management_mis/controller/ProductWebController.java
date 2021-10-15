package io.changsoft.fleet_management_mis.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.changsoft.fleet_management_mis.model.ProductDTO;
import io.changsoft.fleet_management_mis.service.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductWebController {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ProductWebController.class);

	private ProductService productService;

	@Autowired
	ProductWebController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = { "/", "" })
	public String showProductsList(Model model) {
		LOGGER.log(Level.DEBUG, "ProductController.showProductsList() :: Start");
		model.addAttribute("products", productService.findAll());
		LOGGER.log(Level.INFO,
//		System.out.println(
				"ProductController.showProductsList() :: productsSize = " + productService.findAll().size());
		LOGGER.log(Level.DEBUG, "ProductController.showProductsList() :: End");
		return "products/index";
	}

	@GetMapping(value = { "/edit", "/edit/{id}" })
	public String showUpdateForm(@PathVariable("id") Optional<Long> id, Model model) {
		if (id.isPresent()) {
			ProductDTO oldProduct = productService.get(id.get());
			model.addAttribute("product", oldProduct);
		} else {
			model.addAttribute("product", new ProductDTO());
		}
		model.addAttribute("products", productService.findAll());
		return "products/update";
	}

	@PostMapping(path = { "/edit", "/edit/{id}" })
	public String updateProduct(@PathVariable("id") Optional<Long> id, @Valid ProductDTO product, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			// product.setId(id.get());
			return "products/update";
		}

		if (!id.isPresent()) { // check if {id} present ? update param : create param
			productService.create(product);
		} else {
			productService.update(id.get(), product);
		}

		return "redirect:/products";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		ProductDTO product = productService.get(id);
		if (product == null) {
			model.addAttribute("error", "Invalid product with ID : " + id);
		} else {
			productService.delete(product.getId());
		}
		return "redirect:/products";
	}
}
