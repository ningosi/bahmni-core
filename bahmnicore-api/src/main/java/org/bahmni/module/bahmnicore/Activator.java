package org.bahmni.module.bahmnicore;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bahmni.module.bahmnicore.properties.BahmniCoreProperties;
import org.openmrs.GlobalProperty;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.util.OpenmrsConstants;

public class Activator extends BaseModuleActivator {

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void started() {
		AdministrationService service = Context.getAdministrationService();

		log.info("Started the Bahmni Core module");
		BahmniCoreProperties.load();

		GlobalProperty object = service.getGlobalPropertyObject(OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_IDENTIFIER_SEARCH_MATCH_MODE);
		if(object == null ){
			object = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_SEARCH_MATCH_ANYWHERE);
		}
		if(object.getPropertyValue() == null) {
			object.setPropertyValue(OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_SEARCH_MATCH_ANYWHERE);
			service.saveGlobalProperty(object);

		}
		else if(object.getPropertyValue() != null && !(object.getPropertyValue().equals(OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_SEARCH_MATCH_ANYWHERE))) {
			//replace the value with anywhere
			object.setPropertyValue(OpenmrsConstants.GLOBAL_PROPERTY_PATIENT_SEARCH_MATCH_ANYWHERE);
			service.saveGlobalProperty(object);
		}
    }

	@Override
	public void stopped() {
		log.info("Stopped the Bahmni Core module");
	}
}
