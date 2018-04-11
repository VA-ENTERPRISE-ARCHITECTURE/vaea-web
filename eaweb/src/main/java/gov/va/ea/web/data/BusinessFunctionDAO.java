package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.BusinessFunction;

@Repository
public class BusinessFunctionDAO extends BaseDAO {

	public List<BusinessFunction> getBfs(String bfId) {
		return jdbcTemplate.query(BRM_QUERY, new Object[] { new BigDecimal(bfId) }, new BfMapper());
	}

	private static final class BfMapper implements RowMapper<BusinessFunction> {
		public BusinessFunction mapRow(ResultSet rs, int rowNumber) throws SQLException {
			BusinessFunction bf = new BusinessFunction();
			bf.setBfId(rs.getString("BUSINESS_FUNCTION_ID"));
			bf.setElementId(rs.getString("ELEMENT_ID"));
			bf.setBfName(rs.getString("BUSINESS_FUNCTION_NAME"));
			bf.setParentBf(rs.getString("PARENT_BUSINESS_FUNCTION"));
			bf.setHierarchy_number(rs.getString("HIERARCHY_NUMBER"));
			bf.setOrg(rs.getString("ORGANIZATION_ACRONYM"));
			bf.setLevel(rs.getInt("BRM_LEVEL"));
			return bf;
		}
	}
	
	private static String BRM_QUERY = "select master.ELEMENT_ID, master.BUSINESS_FUNCTION_ID, master.BUSINESS_FUNCTION_NAME,master.HIERARCHY_NUMBER, master.PARENT_BUSINESS_FUNCTION, master.BRM_LEVEL, org.ORGANIZATION_ACRONYM, org.ORGANIZATION_NAME, org.PARENT_0_ORGANIZATION from ("
			+ " select ELEMENT_ID, BUSINESS_FUNCTION_ID, BUSINESS_FUNCTION_NAME,HIERARCHY_NUMBER, ORGANIZATION_ID, LEVEL as BRM_LEVEL, PARENT_BUSINESS_FUNCTION from  ee.element_attr_c46"
			+ " START WITH BUSINESS_FUNCTION_ID = ? "
			+ " connect by prior BUSINESS_FUNCTION_ID = PARENT_BUSINESS_FUNCTION ORDER SIBLINGS BY ABS(REPLACE(hierarchy_number, '.', ''))) master"
			+ " left join ee.ELEMENT_ATTR_C53 org on master.ORGANIZATION_ID = org.ORGANIZATION_ID";
}
