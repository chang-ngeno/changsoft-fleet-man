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

import io.changsoft.fleet_management_mis.model.SystemParameterDTO;
import io.changsoft.fleet_management_mis.service.SystemParameterService;

@Controller
@RequestMapping(value = "/parameters")
public class ParameterController {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(ParameterController.class);

	private SystemParameterService parameterService;

	@Autowired
	ParameterController(SystemParameterService parameterService) {
		this.parameterService = parameterService;
	}

	@GetMapping(value = { "/", "" })
	public String showParametersList(Model model) {
		LOGGER.log(Level.DEBUG, "ParameterController.showParametersList() :: Start");
		model.addAttribute("parameters", parameterService.findAll());
		LOGGER.log(Level.INFO,
//		System.out.println(
				"ParameterController.showParametersList() :: parametersSize = " + parameterService.findAll().size());
		LOGGER.log(Level.DEBUG, "ParameterController.showParametersList() :: End");
		return "parameters/index";
	}

	@GetMapping(value = { "/edit", "/edit/{id}" })
	public String showUpdateForm(@PathVariable("id") Optional<Long> id, Model model) {
		if (id.isPresent()) {
			SystemParameterDTO oldParameter = parameterService.get(id.get());
			model.addAttribute("parameter", oldParameter);
		} else {
			model.addAttribute("parameter", new SystemParameterDTO());
		}
		model.addAttribute("parameters", parameterService.findAll());
		return "parameters/update";
	}

	@PostMapping(path = { "/edit", "/edit/{id}" })
	public String updateParameter(@PathVariable("id") Optional<Long> id, @Valid SystemParameterDTO parameter,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			// parameter.setId(id.get());
			return "parameters/update";
		}

		if (!id.isPresent()) { // check if {id} present ? update param : create param
			if (parameter.getParent() == 0L) { // check if {parentId == 0} ? no parent param : param parent present
				parameter.setParent(null);
			}
			parameterService.create(parameter);
		} else {
			parameterService.update(id.get(), parameter);
		}

		return "redirect:/parameters";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteParameter(@PathVariable("id") long id, Model model) {
		SystemParameterDTO parameter = parameterService.get(id);
		if (parameter == null) {
			model.addAttribute("error", "Invalid parameter with ID : " + id);
		} else {
			parameterService.delete(parameter.getId());
		}
		return "redirect:/parameters";
	}
}
