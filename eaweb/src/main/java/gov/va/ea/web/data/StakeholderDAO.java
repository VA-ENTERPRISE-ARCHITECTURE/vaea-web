package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.Stakeholder;

@Repository
public class StakeholderDAO extends BaseDAO{

	public List<Stakeholder> getStakeholders(String vasiId) {
		return jdbcTemplate.query(STAKEHOLDER_QUERY, new Object[] { new BigDecimal(vasiId) },new StakeholderMapper());
	}

	
	private static final class StakeholderMapper implements RowMapper<Stakeholder> {
		public Stakeholder mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Stakeholder stakeholder = new Stakeholder();
			
			stakeholder.setName(rs.getString("name"));
			stakeholder.setTitle(rs.getString("title"));
			stakeholder.setEmail(rs.getString("email"));
			stakeholder.setPhone(rs.getString("phone"));
			stakeholder.setType(rs.getString("type"));
			stakeholder.setOrganization(rs.getString("organization_name"));			
			return stakeholder;
		}
	}
	
	
	private static String STAKEHOLDER_QUERY = "SELECT (last_name||', '||first_name) name"
			+ ", title"
			+ ", phone"
			+ ", lower(email) email"
			+ ", org.organization_name "
			+ ", pl.description type "
			+ "FROM ee.element_attr_c403 system_stakeholder  "
			+ "LEFT outer JOIN ee.element_attr_c402 stakeholder on stakeholder.stakeholder_id = system_stakeholder.stakeholder_id  "
			+ "LEFT outer JOIN ee.element_attr_c53 org on stakeholder.organization_id = org.organization_id  "
			+ "LEFT OUTER JOIN ee.list_option pl on pl.option_id = system_stakeholder.stakeholder_type_id "
			+ "WHERE system_stakeholder.system_id =  ?";
	
	}
