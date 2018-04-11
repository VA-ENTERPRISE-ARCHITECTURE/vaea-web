package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.VASystem;

@Repository
public class SystemDAO extends BaseDAO{

	public VASystem getSystem(String vasiId) {
		VASystem system = null;
		system = jdbcTemplate.queryForObject(SYSTEM_QUERY, new Object[] { new BigDecimal(vasiId) }, new SystemMapper());

		return system;
	}

	public boolean isVASIDBAccessible() {
		int i = 0;
		try {
			i = jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
		} catch (Exception e) {
			System.out.println("Not able to establish connection to VASI Database.");
			return false;
		}

		if (i == 1) {
			System.out.println("Successfully connected to VASI Database.");
			return true;
		}
		return false;
	}

	private static final class SystemMapper implements RowMapper<VASystem> {
		public VASystem mapRow(ResultSet rs, int rowNumber) throws SQLException {
			VASystem system = new VASystem();
			system.setSystemID(rs.getBigDecimal("system_id"));
			system.setAcronym(rs.getString("system_acronym"));
			system.setName(rs.getString("system_name"));
			system.setDescription(rs.getString("system_description"));
			system.setStatus(rs.getString("status"));
			system.setSystemCriticality(rs.getString("system_criticality"));
			system.setType(rs.getString("system_type"));
			system.setProductType(rs.getString("product_type"));
			system.setParentOrganization(rs.getString("parent_organization"));
			system.setSponsorOrganization(rs.getString("sponsor_organization"));
			system.setOitOfficeOfResponsibility(rs.getString("responsible_org"));
			system.setPortfolio(rs.getString("it_portfolio"));
			system.setExternalFacing(rs.getString("external_facing"));

			return system;
		}
	}
	
	private static String SYSTEM_QUERY = "SELECT system_id, system_acronym, system_name, port.name it_portfolio"
			+ " ,DECODE(external_facing_check,0,'No',1,'Yes', '') external_facing"
			+ " ,CASE WHEN org_level < 2 THEN organization_acronym ELSE nvl(parent_org, organization_acronym) END parent_organization"
			+ " ,organization_name sponsor_organization" + " ,pl_status.description status"
			+ " ,pl_oitoo.description responsible_org" + " ,SUBSTR(pl_criticality.description,4) system_criticality"
			+ " ,pl_type.description system_type" + " ,pl_cots.description product_type" + " ,system_description"
			+ "  FROM ee.element_attr_c56 system"
			+ "  LEFT OUTER JOIN (SELECT org.organization_id, org.organization_name, org.organization_acronym, org.org_level, porg.organization_acronym parent_org FROM ee.element_attr_c53 org,  ee.element_attr_c53 porg WHERE org.parent_0_organization = porg.organization_id) o ON system.organization_id = o.organization_id"
			+ "  LEFT OUTER JOIN ee.ELEMENT_ATTR_C1416 port on port.PORTFOLIO_ID = system.EPMO_PORTFOLIO"
			+ "  LEFT OUTER JOIN ee.list_option pl_status on pl_status.option_id = system.SYSTEM_STATUS_PICK_LIST"
			+ "  LEFT OUTER JOIN ee.list_option pl_oitoo on pl_oitoo.option_id = system.oit_office_of_responsibility"
			+ "  LEFT OUTER JOIN ee.list_option pl_criticality on pl_criticality.option_id = system.system_criticality_pick_list"
			+ "  LEFT OUTER JOIN ee.list_option pl_type on pl_type.option_id = system.system_type_pick_list"
			+ "  LEFT OUTER JOIN ee.list_option pl_cots on pl_cots.option_id = system.product_type_pick_list"
			+ " WHERE system_id = ?";

}
