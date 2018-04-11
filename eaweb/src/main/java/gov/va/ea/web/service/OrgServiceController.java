package gov.va.ea.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.va.ea.web.data.OrganizationDAO;
import gov.va.ea.web.model.Organization;
import gov.va.ea.web.model.tree.ChartConfig;
import gov.va.ea.web.model.tree.Contact;
import gov.va.ea.web.model.tree.NodeStructure;
import gov.va.ea.web.model.tree.Text;
import gov.va.ea.web.utils.WebUtils;

@RestController
public class OrgServiceController {
	private static String DETAIL_URL_1 = "https://vaausdarapp41.aac.dva.va.gov/ee/request/folder/e/";
	private static String DETAIL_URL_2 = "/c/53/nt/28264?id=18091";

	@Autowired
	OrganizationDAO orgDAO;
	
	@RequestMapping(method = RequestMethod.GET, value = "/nsys/{orgId}")
	public int getNumberOfSystems(@PathVariable String orgId) {
		return orgDAO.getNumberOfSystems(orgId);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/orgs/{orgId}/{layout}")
	public ChartConfig getOrganizations(@PathVariable String orgId, @PathVariable String layout) {
		if(orgId == null || orgId.isEmpty()) {
			orgId = "1";
		}
		if(layout == null || layout.isEmpty()) {
			layout = "NORTH";
		}
		ChartConfig chartConfig = new ChartConfig();
		chartConfig.setChart(WebUtils.getWebConfig(layout));
		
		List<Organization> orgs = orgDAO.getOrgs(orgId);
		NodeStructure parentNode = null;
		NodeStructure rootNode = null;
		NodeStructure currentNode = null;
		Map<String, NodeStructure> nodeMap = new HashMap<String, NodeStructure>();
		for (Organization org: orgs) {
			currentNode = getNode(org, orgs, layout);
			nodeMap.put(org.getOrgId(), currentNode );
			if(org.getParentOrg() != null && !org.getOrgId().equals(orgId)) {
				parentNode = nodeMap.get(org.getParentOrg());
				if(parentNode.getChildren() == null) {
					parentNode.setChildren(new ArrayList<NodeStructure>());					
				} 
				parentNode.getChildren().add(currentNode);					
			} else {
				rootNode = currentNode;
			}
		}
		chartConfig.setNodeStructure(rootNode);
		return chartConfig;
    }
	
	private NodeStructure getNode(Organization org, List<Organization> orgs, String layout) {
		NodeStructure ns = new NodeStructure();
		ns.setHTMLid("rNode" + org.getOrgId());
		//
		ns.setHTMLclass(getStyle(org.getRootParentOrg()));
		ns.setImage(getImage(org.getRootParentOrg()));
		if(layout.equalsIgnoreCase("NORTH") && org.getLevel() > 2) {
			ns.setStackChildren(true);
		}
		Text text = new Text();
		Contact contact = new Contact();
		text.setName(org.getOrgName());
		text.setTitle(getTitle(org));
		contact.setVal("show details");
		contact.setHref(DETAIL_URL_1 + org.getElementId() + DETAIL_URL_2);
		contact.setTarget("_blank");
		text.setContact(contact);
		//text.setNumberOfSystems(Integer.toString(org.getNumberOfSystems()));
		ns.setText(text);
		ns.setParentId(org.getParentOrg());
		if(org.getLevel() >1 ) {
			if(isParent(orgs, org.getOrgId())) {
				ns.setCollapsed(true);
			}
		}
		return ns;
	}
	
	private String getStyle(String org) {
		if (org == null || org.isEmpty() || org.equalsIgnoreCase("1")) {
			return "osva";
		} else if (org.equalsIgnoreCase("3")){
				return "vha";
    	} else if (org.equalsIgnoreCase("2")){
    		return "vba";
    	} else if (org.equalsIgnoreCase("4")){
    		return "nca";
    	} else if (org.equalsIgnoreCase("7")){
    		return "oit";
    	} else if (org.equalsIgnoreCase("6")){
    		return "ohra";
    	} else if (org.equalsIgnoreCase("8")){
    		return "om";
    	} else if (org.equalsIgnoreCase("13")){
    		return "oalc";
    	} else {
    		return "corp";
    	}
	}
	
	private String getImage(String org) {
		if (org == null || org.isEmpty() || org.equalsIgnoreCase("1")) {
			return "../img/org/osva.png";
		} else if (org.equalsIgnoreCase("3")){
				return "../img/org/vha.png";
    	} else if (org.equalsIgnoreCase("2")){
    		return "../img/org/vba.png";
    	} else if (org.equalsIgnoreCase("4")){
    		return "../img/org/nca.png";
    	} else if (org.equalsIgnoreCase("7")){
    		return "../img/org/oit.png";
    	} else if (org.equalsIgnoreCase("6")){
    		return "../img/org/ohra.png";
    	} else if (org.equalsIgnoreCase("8")){
    		return "../img/org/om.png";
    	} else if (org.equalsIgnoreCase("13")){
    		return "../img/org/oalc.png";
    	} else {
    		return "../img/org/corp.png";
    	}
	}
	
	private boolean isParent(List<Organization> orgs, String orgId) {
		for (Organization org: orgs) {
			if(org.getParentOrg() != null && org.getParentOrg().equalsIgnoreCase(orgId)) {
				return true;
			}
		}
		return false;
	}
	
	private String getTitle(Organization org) {
		String temp = "";
		
		if (org.getAcronym() != null && !org.getAcronym().isEmpty()) {
			temp = temp + org.getAcronym();
		}
		if (org.getMailStop() != null && !org.getMailStop().isEmpty()) {
			temp = temp + "[" + org.getMailStop() + "]";
		}
		return temp;
	}
		
}
