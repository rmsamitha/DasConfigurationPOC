package das.config.service.clients;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import das.config.service.stubs.EventReceiverAdminServiceStub;
import das.config.service.stubs.EventReceiverAdminServiceStub.BasicInputAdapterPropertyDto;
import das.config.service.stubs.EventReceiverAdminServiceStub.DeployTextEventReceiverConfiguration;
import das.config.service.stubs.EventReceiverAdminServiceStub.DeployXmlEventReceiverConfiguration;
import das.config.service.stubs.EventReceiverAdminServiceStub.EventMappingPropertyDto;
import das.config.service.stubs.EventStreamAdminServiceStub;

public class ReceiverAdminServiceClient {
	private final String serviceName = "EventReceiverAdminService";
	private EventReceiverAdminServiceStub eventReceiverAdminServiceStub;
	private String endPoint;

	public ReceiverAdminServiceClient(String backEndUrl, String sessionCookie)
			throws AxisFault {
		this.endPoint = backEndUrl + "/services/" + serviceName;
		eventReceiverAdminServiceStub = new EventReceiverAdminServiceStub(endPoint);
		// Authenticate Your stub from sessionCooke
		ServiceClient serviceClient;
		Options option;

		serviceClient = eventReceiverAdminServiceStub._getServiceClient();
		option = serviceClient.getOptions();
		// option.setAction("urn:listServices");
		// System.out.println(option.getAction());
		// option.setTo(new EndpointReference(endPoint));
		option.setManageSession(true);
		option.setProperty(
				org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING,
				sessionCookie);

	}

	public void deployEventReceiverConfiguration(String eventReceiverName,String streamNameWithVersion,String eventAdapterType) {
		
		try {
			eventReceiverAdminServiceStub=new EventReceiverAdminServiceStub();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeployTextEventReceiverConfiguration textEventReceiverConfiguration=new DeployTextEventReceiverConfiguration();
		textEventReceiverConfiguration.setEventReceiverName(eventReceiverName);
		textEventReceiverConfiguration.setStreamNameWithVersion(streamNameWithVersion);
		textEventReceiverConfiguration.setEventAdapterType(eventAdapterType);
		
		BasicInputAdapterPropertyDto y[]=new BasicInputAdapterPropertyDto[1];
		BasicInputAdapterPropertyDto prop=new BasicInputAdapterPropertyDto();
		
		prop.setKey("events.duplicated.in.cluster");
		prop.setValue("false");
		y[0]=prop;
		textEventReceiverConfiguration.setInputPropertyConfiguration(y);
		/*EventMappingPropertyDto[] x= new EventMappingPropertyDto[1];
		x[0]=new EventMappingPropertyDto();
		x[0].setName("customMapping");
		x[0].setDefaultValue("disable");
		x[0].setType("wso2event");
		
		textEventReceiverConfiguration.setInputMappings(x); */
		//textEventReceiverConfiguration.setInputMappings();
		try {
			eventReceiverAdminServiceStub.deployTextEventReceiverConfiguration(textEventReceiverConfiguration);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*DeployXmlEventReceiverConfiguration xmlEventReceiverConfiguration=new DeployXmlEventReceiverConfiguration();
		xmlEventReceiverConfiguration.setEventAdapterType(eventAdapterType);
		xmlEventReceiverConfiguration.setEventReceiverName(eventReceiverName);
		xmlEventReceiverConfiguration.setStreamNameWithVersion(streamNameWithVersion);
		xmlEventReceiverConfiguration.setEventAdapterType(eventAdapterType);
		EventMappingPropertyDto[] x= new EventMappingPropertyDto[1];
		x[0]=new EventMappingPropertyDto();
		x[0].setName("customMapping");
		x[0].setDefaultValue("disable");
		x[0].setType("wso2event");
		
		xmlEventReceiverConfiguration.setInputMappings(x);
		//textEventReceiverConfiguration.setInputMappings();
		try {
			eventReceiverAdminServiceStub.deployXmlEventReceiverConfiguration(xmlEventReceiverConfiguration);
		} catch (RemoteException e) {
			e.printStackTrace();
		}*/
		
	}
	
}
