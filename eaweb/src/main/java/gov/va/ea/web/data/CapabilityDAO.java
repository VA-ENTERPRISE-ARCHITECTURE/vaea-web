package gov.va.ea.web.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.Capability;

@Repository
public class CapabilityDAO extends BaseDAO {

    public List<Capability> getCapabilities() {
	return jdbcTemplate.query(BRM_CAPABILITY_QUERY, new CapabilityMapper());
    }

    private static final class CapabilityMapper implements RowMapper<Capability> {
	public Capability mapRow(ResultSet rs, int rowNumber) throws SQLException {
	    Capability capability = new Capability();
	    capability.setBusinessFunctionId(rs.getString("BUSINESS_FUNCTION_ID"));
	    capability.setBusinessFunctionName(rs.getString("BUSINESS_FUNCTION_NAME"));
	    capability.setHierarchyNumber(rs.getString("HIERARCHY_NUMBER"));
	    return capability;
	}
    }

    private static String BRM_CAPABILITY_QUERY = "select business_function_id, hierarchy_number, bff.BUSINESS_FUNCTION_NAME \r\n"
	    + "from ee.element_attr_c46 bff \r\n" + "where business_function_level = 2 \r\n"
	    + "order by SUBSTR(hierarchy_number, 1,1), ABS(REPLACE(hierarchy_number, '.', ''))";
}
