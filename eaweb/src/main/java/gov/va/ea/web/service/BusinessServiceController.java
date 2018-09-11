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

import gov.va.ea.web.data.BusinessFunctionDAO;
import gov.va.ea.web.data.CapabilityDAO;
import gov.va.ea.web.model.BusinessFunction;
import gov.va.ea.web.model.Capability;
import gov.va.ea.web.model.tree.ChartConfig;
import gov.va.ea.web.model.tree.Contact;
import gov.va.ea.web.model.tree.NodeStructure;
import gov.va.ea.web.model.tree.Text;
import gov.va.ea.web.utils.WebUtils;

@RestController
public class BusinessServiceController {
    private static String BF_DETAIL_URL_1 = "https://vaausdarapp41.aac.dva.va.gov/ee/request/folder/e/";
    private static String BF_DETAIL_URL_2 = "/c/46/nt/-1?name-lookup=BUS_HOME";

    @Autowired
    BusinessFunctionDAO bfDao;
    @Autowired
    CapabilityDAO capabilityDao;

    @RequestMapping(method = RequestMethod.GET, value = "/capabilities")
    public List<Capability> getCapablities() {
	List<Capability> result = capabilityDao.getCapabilities();
	return result;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/bffs/{bfId}/{layout}")
    public ChartConfig getBusinessFunctions(@PathVariable String bfId, @PathVariable String layout) {

	if (bfId == null || bfId.isEmpty()) {
	    bfId = "1054";
	}
	if (layout == null || layout.isEmpty()) {
	    layout = "NORTH";
	}
	ChartConfig chartConfig = new ChartConfig();
	chartConfig.setChart(WebUtils.getWebConfig(layout));

	List<BusinessFunction> bffs = bfDao.getBfs(bfId);
	NodeStructure parentNode = null;
	NodeStructure rootNode = null;
	NodeStructure currentNode = null;
	Map<String, NodeStructure> nodeMap = new HashMap<String, NodeStructure>();
	for (BusinessFunction bf : bffs) {
	    currentNode = getNode(bf, bffs, layout);
	    nodeMap.put(bf.getBfId(), currentNode);
	    if (bf.getParentBf() != null && !bf.getBfId().equals(bfId)) {
		parentNode = nodeMap.get(bf.getParentBf());
		if (parentNode.getChildren() == null) {
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

    private NodeStructure getNode(BusinessFunction bf, List<BusinessFunction> bffs, String layout) {
	NodeStructure ns = new NodeStructure();
	ns.setHTMLid("node" + bf.getBfId());
	//
	ns.setHTMLclass(getStyle(bf.getOrg()));
	ns.setImage(getImage(bf.getOrg()));
	if (layout.equalsIgnoreCase("NORTH") && bf.getLevel() > 2) {
	    ns.setStackChildren(true);
	}
	Text text = new Text();
	Contact contact = new Contact();
	text.setTitle(bf.getHierarchy_number());
	text.setName(bf.getBfName());
	contact.setVal("show details");
	contact.setHref(BF_DETAIL_URL_1 + bf.getElementId() + BF_DETAIL_URL_2);
	contact.setTarget("_blank");
	text.setContact(contact);
	// text.setNumberOfSystems(Integer.toString(getNumberOfSystems()));
	ns.setText(text);
	ns.setParentId(bf.getParentBf());
	if (bf.getLevel() > 1) {
	    if (isParent(bffs, bf.getBfId())) {
		ns.setCollapsed(true);
	    }
	}
	return ns;
    }

    private String getStyle(String org) {
	if (org == null || org.isEmpty()) {
	    return "osva";
	} else if (org.equalsIgnoreCase("vha")) {
	    return "vha";
	} else if (org.equalsIgnoreCase("vba")) {
	    return "vba";
	} else if (org.equalsIgnoreCase("nca")) {
	    return "nca";
	} else if (org.equalsIgnoreCase("oi&t")) {
	    return "oit";
	} else if (org.equalsIgnoreCase("ohra")) {
	    return "ohra";
	} else if (org.equalsIgnoreCase("om")) {
	    return "om";
	} else if (org.equalsIgnoreCase("oalc")) {
	    return "oalc";
	} else {
	    return "corp";
	}
    }

    private String getImage(String org) {
	if (org == null || org.isEmpty()) {
	    return "../img/org/osva.png";
	} else if (org.equalsIgnoreCase("vha")) {
	    return "../img/org/vha.png";
	} else if (org.equalsIgnoreCase("vba")) {
	    return "../img/org/vba.png";
	} else if (org.equalsIgnoreCase("nca")) {
	    return "../img/org/nca.png";
	} else if (org.equalsIgnoreCase("oi&t")) {
	    return "../img/org/oit.png";
	} else if (org.equalsIgnoreCase("ohra")) {
	    return "../img/org/ohra.png";
	} else if (org.equalsIgnoreCase("om")) {
	    return "../img/org/om.png";
	} else if (org.equalsIgnoreCase("oalc")) {
	    return "../img/org/oalc.png";
	} else {
	    return "../img/org/corp.png";
	}
    }

    private boolean isParent(List<BusinessFunction> bffs, String bfId) {
	for (BusinessFunction bf : bffs) {
	    if (bf.getParentBf() != null && bf.getParentBf().equalsIgnoreCase(bfId)) {
		return true;
	    }
	}
	return false;
    }

}
