package com.copilot.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// write a controller to handle MT 102 Multiple Customer Credit Transfer
//import the required packages
import com.copilot.sample.model.SwiftMT103;
import com.copilot.sample.service.SwiftMT103Service;

//annotate the class as rest controller
@RestController
//define the base url
@RequestMapping("/swift")
public class SwiftMT103Controller {
	// autowire the swift service
	@Autowired
	private SwiftMT103Service swiftMT103Service;

	// define the post mapping to handle the request
	@PostMapping("/mt103")
	// define the method to handle the request
	public ResponseEntity<SwiftMT103> createMT103(@RequestBody SwiftMT103 swiftMT103) {
		// call the service method to create the swift mt103
		SwiftMT103 swiftMT1031 = swiftMT103Service.create(swiftMT103);
		// return the response entity
		return new ResponseEntity<>(swiftMT1031, HttpStatus.CREATED);
	}

	// define the get mapping to handle the request
	@GetMapping("/mt103")
	// define the method to handle the request
	public ResponseEntity<List<SwiftMT103>> getAllMT103() {
		// call the service method to get all the swift mt103
		List<SwiftMT103> swiftMT103List = swiftMT103Service.findAll();
		// return the response entity
		return new ResponseEntity<>(swiftMT103List, HttpStatus.OK);
	}

	// define the get mapping to handle the request
	@GetMapping("/mt103/{id}")
	// define the method to handle the request
	public ResponseEntity<SwiftMT103> getMT103ById(@PathVariable("id") String id) {
		// call the service method to get the swift mt103 by id
		SwiftMT103 swiftMT103 = swiftMT103Service.findById(id);
		// return the response entity
		return new ResponseEntity<>(swiftMT103, HttpStatus.OK);
	}

	// define the put mapping to handle the request
	@PutMapping("/mt103/{id}")
	// define the method to handle the request
	public ResponseEntity<SwiftMT103> updateMT103(@PathVariable("id") String id, @RequestBody SwiftMT103 swiftMT103) {
		// call the service method to update the swift mt103
		SwiftMT103 swiftMT1031 = swiftMT103Service.update(swiftMT103);
		// return the response entity
		return new ResponseEntity<>(swiftMT1031, HttpStatus.OK);
	}

	// define the delete mapping to handle the request
	@DeleteMapping("/mt103/{id}")
	// define the method to handle the request
	public ResponseEntity<?> deleteMT103(@PathVariable("id") String id) {
		// call the service method to delete the swift mt103
		swiftMT103Service.deleteMT103(id);
		// return the response entity
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
