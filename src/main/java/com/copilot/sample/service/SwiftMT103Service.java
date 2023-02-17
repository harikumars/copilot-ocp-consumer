package com.copilot.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.copilot.sample.model.SwiftMT103;
import com.copilot.sample.repository.SwiftMT103Dao;

//Add service configuration to SwiftMT103Service class
@Service
public class SwiftMT103Service {
	// Autowire SwiftMT103Dao
	@Autowired
	private SwiftMT103Dao swiftMT103Dao;

	public List<SwiftMT103> findAll() {
		return swiftMT103Dao.findAll();
	}

	public SwiftMT103 findById(String swiftMT103Id) {
		return swiftMT103Dao.findById(swiftMT103Id);
	}

	public SwiftMT103 create(SwiftMT103 swiftMT103) {
		return swiftMT103Dao.create(swiftMT103);
	}

	public SwiftMT103 update(SwiftMT103 swiftMT103) {
		return swiftMT103Dao.update(swiftMT103);
	}

	public void delete(SwiftMT103 swiftMT103) {
		swiftMT103Dao.delete(swiftMT103);
	}

	public void deleteMT103(String id) {
	}
}