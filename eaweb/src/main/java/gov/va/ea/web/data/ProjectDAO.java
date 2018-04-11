package gov.va.ea.web.data;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gov.va.ea.web.model.Project;

@Repository
public class ProjectDAO extends BaseDAO{

	public List<Project> getProjects(String vasiId) {
		return jdbcTemplate.query(PROJECT_QUERY, new Object[] { new BigDecimal(vasiId) },new ProjectMapper());
	}

	private static final class ProjectMapper implements RowMapper<Project> {
		public Project mapRow(ResultSet rs, int rowNumber) throws SQLException {
			Project project = new Project();
			
			project.setName(rs.getString("project_name"));
			project.setStatus(rs.getString("status"));
			project.setEPSCode(rs.getString("eps_code"));
			project.setResponsibleOrg(rs.getString("office_of_responsibility"));
			project.setProjectId(rs.getBigDecimal("proj_id"));						
			return project;
		}
	}
	
	private static String PROJECT_QUERY = "SELECT system_project.proj_id, project_name, office_of_responsibility, eps_code ,pl.description status "
			+ "FROM ee.element_attr_c67 system_project "
			+ "LEFT outer JOIN ee.element_attr_c54 project on system_project.proj_id = project.proj_id "
			+ "LEFT OUTER JOIN ee.list_option pl on pl.option_id = project.project_status_pick_list "
			+ "WHERE system_project.system_id = ?";

}
