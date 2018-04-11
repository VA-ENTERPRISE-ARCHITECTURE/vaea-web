package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.SystemOfSystem;

@Repository
public class SystemOfSystemDAO extends BaseDAO {

	public List<SystemOfSystem> getSoSs(String vasiId) {
		BigDecimal id = new BigDecimal(vasiId);
		return jdbcTemplate.query(SOS_QUERY, new Object[] { id, id}, new SoSMapper());
	}

	private static final class SoSMapper implements RowMapper<SystemOfSystem> {
		public SystemOfSystem mapRow(ResultSet rs, int rowNumber) throws SQLException {
			SystemOfSystem sos = new SystemOfSystem();
			sos.setChildId(rs.getString("child_system"));
			sos.setChildName(rs.getString("child_name"));
			sos.setChildAcronym(rs.getString("child_acronym"));

			sos.setParentId(rs.getString("parent_system"));
			sos.setParentName(rs.getString("parent_name"));
			sos.setParentAcronym(rs.getString("parent_acronym"));

			return sos;
		}
	}

	private static String SOS_QUERY = "SELECT DISTINCT"
			+ " sos.child_system, cs.system_name child_name, cs.system_acronym child_acronym,"
			+ " sos.parent_system, ps.system_name parent_name, ps.system_acronym parent_acronym"
			+ " FROM ee.element_attr_c66 sos"
			+ " LEFT OUTER JOIN ee.element_attr_c56 ps on sos.parent_system = ps.system_id"
			+ " LEFT OUTER JOIN ee.element_attr_c56 cs on sos.child_system = cs.system_id"
			+ " CONNECT BY PRIOR sos.child_system = sos.parent_system"
			+ " START WITH sos.parent_system IN ("
			+ " SELECT parent_system"
			+ " FROM ee.element_attr_c66"
			+ " START WITH child_system = ?"
			+ " CONNECT BY PRIOR parent_system = child_system"
			+ " UNION"
			+ " SELECT system_id"
			+ " FROM ee.element_attr_c56"
			+ " WHERE system_id = ?)";
}
