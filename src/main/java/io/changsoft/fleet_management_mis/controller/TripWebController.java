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

import io.changsoft.fleet_management_mis.model.TripDTO;
import io.changsoft.fleet_management_mis.service.TripService;

@Controller
@RequestMapping(value = "/trips")
public class TripWebController {

	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(TripWebController.class);

	private TripService tripService;

	@Autowired
	TripWebController(TripService tripService) {
		this.tripService = tripService;
	}

	@GetMapping(value = { "/", "" })
	public String showTripsList(Model model) {
		LOGGER.log(Level.DEBUG, "TripController.showTripsList() :: Start");
		model.addAttribute("trips", tripService.findAll());
		LOGGER.log(Level.INFO,
//		System.out.println(
				"TripController.showTripsList() :: tripsSize = " + tripService.findAll().size());
		LOGGER.log(Level.DEBUG, "TripController.showTripsList() :: End");
		return "trips/index";
	}

	@GetMapping(value = { "/edit", "/edit/{id}" })
	public String showUpdateForm(@PathVariable("id") Optional<Long> id, Model model) {
		if (id.isPresent()) {
			TripDTO oldTrip = tripService.get(id.get());
			model.addAttribute("trip", oldTrip);
		} else {
			model.addAttribute("trip", new TripDTO());
		}
		model.addAttribute("trips", tripService.findAll());
		return "trips/update";
	}

	@PostMapping(path = { "/edit", "/edit/{id}" })
	public String updateTrip(@PathVariable("id") Optional<Long> id, @Valid TripDTO trip, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			// trip.setId(id.get());
			return "trips/update";
		}

		if (!id.isPresent()) { // check if {id} present ? update param : create param
			tripService.create(trip);
		} else {
			tripService.update(id.get(), trip);
		}

		return "redirect:/trips";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteTrip(@PathVariable("id") long id, Model model) {
		TripDTO trip = tripService.get(id);
		if (trip == null) {
			model.addAttribute("error", "Invalid trip with ID : " + id);
		} else {
			tripService.delete(trip.getId());
		}
		return "redirect:/trips";
	}
}
