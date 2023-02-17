package com.copilot.sample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.copilot.sample.model.SwiftMT103;

//Add repository configuration to SwiftMT103Dao class
@Repository
public class SwiftMT103Dao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public SwiftMT103Dao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<SwiftMT103> findAll() {
		return namedParameterJdbcTemplate.query("select * from swift_mt103", new SwiftMT103RowMapper());
	}

	public SwiftMT103 findById(String swiftMT103Id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", swiftMT103Id);
		return namedParameterJdbcTemplate.queryForObject("select * from swift_mt103 where id=:id", params,
				new SwiftMT103RowMapper());
	}

	public SwiftMT103 create(SwiftMT103 swiftMT103) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", swiftMT103.getId());
		params.put("message_type", swiftMT103.getMessageType());
		params.put("message_priority", swiftMT103.getMessagePriority());
		params.put("sender", swiftMT103.getSender());
		params.put("receiver", swiftMT103.getReceiver());
		params.put("message", swiftMT103.getMessage());
		namedParameterJdbcTemplate.update(
				"insert into swift_mt103 (id, message_type, message_priority, sender, receiver, message) values (:id, :message_type, :message_priority, :sender, :receiver, :message)",
				params);
		return swiftMT103;
	}

	public SwiftMT103 update(SwiftMT103 swiftMT103) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", swiftMT103.getId());
		params.put("message_type", swiftMT103.getMessageType());
		params.put("message_priority", swiftMT103.getMessagePriority());
		params.put("sender", swiftMT103.getSender());
		params.put("receiver", swiftMT103.getReceiver());
		params.put("message", swiftMT103.getMessage());
		namedParameterJdbcTemplate.update(
				"update swift_mt103 set message_type=:message_type, message_priority=:message_priority, sender=:sender, receiver=:receiver, message=:message where id=:id",
				params);
		return swiftMT103;
	}

	public void delete(SwiftMT103 swiftMT103) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", swiftMT103.getId());
		namedParameterJdbcTemplate.update("delete from swift_mt103 where id=:id", params);
	}

	private static final class SwiftMT103RowMapper implements RowMapper<SwiftMT103> {
		public SwiftMT103 mapRow(ResultSet rs, int rowNum) throws SQLException {
			SwiftMT103 swiftMT103 = new SwiftMT103();
			swiftMT103.setId(rs.getString("id"));
			swiftMT103.setMessageType(rs.getString("message_type"));
			swiftMT103.setMessagePriority(rs.getString("message_priority"));
			swiftMT103.setSender(rs.getString("sender"));
			swiftMT103.setReceiver(rs.getString("receiver"));
			swiftMT103.setMessage(rs.getString("message"));
			return swiftMT103;
		}
	}
}
