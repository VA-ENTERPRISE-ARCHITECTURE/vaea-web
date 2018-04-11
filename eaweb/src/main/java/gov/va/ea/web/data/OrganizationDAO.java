package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import gov.va.ea.web.model.Organization;

@Repository
public class OrganizationDAO extends BaseDAO {

	public List<Organization> getOrgs(String orgId) {
		return jdbcTemplate.query(ORG_QUERY, new Object[] { new BigDecimal(orgId) }, new OrgMapper());
	}
	
	public int getNumberOfSystems(String orgId) {
		return jdbcTemplate.queryForObject(SYS_QUERY, new Object[] { orgId }, Integer.class);
	}

	private static final class OrgMapper implements RowMapper<Organization> {
		public Organization mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Organization org = new Organization();
			org.setOrgId(rs.getString("organization_id"));
			org.setOrgName(rs.getString("organization_name"));
			org.setAcronym(rs.getString("organization_acronym"));
			org.setMailStop(rs.getString("mail_stop"));
			org.setParentOrg(rs.getString("parent_organization"));
			org.setLevel(rs.getInt("org_level"));
			org.setRootParentOrg(rs.getString("parent_0_organization"));
			org.setElementId(rs.getString("element_id"));
			return org;
		}
	}

	private static String ORG_QUERY = "SELECT org.element_id, org.organization_id, org.organization_name, org.organization_acronym, org.mail_stop, org.parent_organization, LEVEL as ORG_LEVEL, org.PARENT_0_ORGANIZATION"
			+ " FROM ee.element_attr_c53 org "
			+ " START WITH org.organization_id = ? "
			+ " CONNECT BY PRIOR org.organization_id = org.parent_organization "
			+ " ORDER SIBLINGS BY org.organization_id";
	
	private static String SYS_QUERY = "select count(*)from ee.element_attr_c56 system where system.organization_id in ("
			+ " SELECT org.organization_id FROM ee.element_attr_c53 org START WITH org.organization_id = ?"
			+ "  CONNECT BY PRIOR org.organization_id = org.parent_organization) and SYSTEM_STATUS_PICK_LIST in (715, 716)";
}
