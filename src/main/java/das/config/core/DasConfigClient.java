package das.config.core;

/**
 * Created by samithac on 13/2/16.
 */
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.carbon.authenticator.stub.LogoutAuthenticationExceptionException;
//import org.wso2.carbon.service.mgt.stub.types.carbon.ServiceMetaData;
//import org.wso2.carbon.service.mgt.stub.types.carbon.ServiceMetaDataWrapper;
import org.wso2.carbon.*;

import das.config.service.clients.LoginAdminServiceClient;
import das.config.service.clients.StreamAdminServiceClient;

import java.rmi.RemoteException;

    public class DasConfigClient {
    public static void main(String[] args)
            throws RemoteException, LoginAuthenticationExceptionException,
            LogoutAuthenticationExceptionException {
        System.setProperty("javax.net.ssl.trustStore", "/home/samithac/wso2-products/wso2das-3.0.0-SNAPSHOT/repository/resources/security/wso2carbon.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
        System.setProperty("javax.net.ssl.trustStoreType", "JKS");
        String backEndUrl = "https://localhost:9443";  //"https://172.21.47.141:9445";

        //login to DAS as admin
        LoginAdminServiceClient login = new LoginAdminServiceClient(backEndUrl);
        String session = login.authenticate("admin", "admin");
        System.out.println("QQQ");
      
        StreamAdminServiceClient streamAdminServiceClient = new StreamAdminServiceClient(backEndUrl, session);

         System.out.println("getEventStreamNames::"+streamAdminServiceClient.getGG());
         streamAdminServiceClient.doSomeTestingTasks();
        //ServiceMetaDataWrapper serviceList = streamAdminServiceClient.listServices();
        //System.out.println("Service Names:");

/*
        for (ServiceMetaData serviceData : serviceList.getServices()) {
            System.out.println(serviceData.getName());
        }
*/        //getStreamNames

        login.logOut();
    }
}