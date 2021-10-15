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

import io.changsoft.fleet_management_mis.model.VehicleDTO;
import io.changsoft.fleet_management_mis.service.VehicleService;

@Controller
@RequestMapping(value = "/vehicles")
public class VehicleWebController {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(VehicleWebController.class);

	private VehicleService vehicleService;

	@Autowired
	VehicleWebController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@GetMapping(value = { "/", "" })
	public String showVehiclesList(Model model) {
		LOGGER.log(Level.DEBUG, "VehicleController.showVehiclesList() :: Start");
		model.addAttribute("vehicles", vehicleService.findAll());
		LOGGER.log(Level.INFO,
//		System.out.println(
				"VehicleController.showVehiclesList() :: vehiclesSize = " + vehicleService.findAll().size());
		LOGGER.log(Level.DEBUG, "VehicleController.showVehiclesList() :: End");
		return "vehicles/index";
	}

	@GetMapping(value = { "/edit", "/edit/{id}" })
	public String showUpdateForm(@PathVariable("id") Optional<Long> id, Model model) {
		if (id.isPresent()) {
			VehicleDTO oldVehicle = vehicleService.get(id.get());
			model.addAttribute("vehicle", oldVehicle);
		} else {
			model.addAttribute("vehicle", new VehicleDTO());
		}
		model.addAttribute("vehicles", vehicleService.findAll());
		return "vehicles/update";
	}

	@PostMapping(path = { "/edit", "/edit/{id}" })
	public String updateVehicle(@PathVariable("id") Optional<Long> id, @Valid VehicleDTO vehicle,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			// vehicle.setId(id.get());
			return "vehicles/update";
		}

		if (!id.isPresent()) { // check if {id} present ? update param : create param
			vehicleService.create(vehicle);
		} else {
			vehicleService.update(id.get(), vehicle);
		}

		return "redirect:/vehicles";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteVehicle(@PathVariable("id") long id, Model model) {
		VehicleDTO vehicle = vehicleService.get(id);
		if (vehicle == null) {
			model.addAttribute("error", "Invalid vehicle with ID : " + id);
		} else {
			vehicleService.delete(vehicle.getId());
		}
		return "redirect:/vehicles";
	}
}
