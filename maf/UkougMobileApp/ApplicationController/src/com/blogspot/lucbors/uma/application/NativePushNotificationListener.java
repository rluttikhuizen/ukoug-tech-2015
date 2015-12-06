package com.blogspot.lucbors.uma.application;

import java.util.HashMap;

import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.exception.AdfException;

import oracle.ateam.sample.mobile.util.ADFMobileLogger;

public class NativePushNotificationListener implements EventListener {
    
    private static ADFMobileLogger sLog = ADFMobileLogger.createLogger(NativePushNotificationListener.class);


    public NativePushNotificationListener() {
    }
    

    public void onMessage(Event event) {
        String msg = event.getPayload();
        System.out.println("#### Message from the Server :" + msg);
        
        // Parse the payload of the push notification
        HashMap payload = null;
        String pushMsg = "No message received";
        try
        {
          payload = (HashMap)JSONBeanSerializationHelper.fromJSON(HashMap.class, msg);
          pushMsg = (String)payload.get("alert");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // Write the push message to app scope to display to the user        
        AdfmfJavaUtilities.setELValue("#{applicationScope.pushMessage}", pushMsg);
        
        // Now open the Sessions Feature and navigate to the appropiate session.
        // The session Id should be part of the pushmessage's payoad
        // 
       
       /*
        String sessionId = pushMsg = (String)payload.get("sessionId");
        ValueExpression ve =
        AdfmfJavaUtilities.getValueExpression("#{applicationScope.sessionId}", String.class);
        
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), sessionId);
        
        AdfmfContainerUtilities.gotoFeature("Sessions");
        
        //and navigate (perhaps this ust be transferred to Sessions feature.
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                          "adf.mf.api.amx.doNavigation", new Object[] {
                                                                          "showSessionById" });
       */
    }

    public void onError(AdfException adfException) {
        System.out.println("#### Error: " + adfException.toString());
        // Write the error into app scope        
        
        sLog.severe("#### Error: " + adfException.toString());
        
        AdfmfJavaUtilities.setELValue("#{applicationScope.errorMessage}", adfException.toString());

    }

  

        public void onOpen(String id) {
                
                System.out.println("Entering onOpen with device Token :" + id);
                
                sLog.severe("Entering onOpen with device Token :" + id);

                
                ValueExpression ve =
                    AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
                ve.setValue(AdfmfJavaUtilities.getAdfELContext(), id);

                RestServiceAdapter adapter = Model.createRestServiceAdapter();
                adapter.clearRequestProperties();
                adapter.setConnectionName("UkougRestConn");
                adapter.setRequestType("POST");
                adapter.setRequestURI("/platform/devices/register");
                adapter.addRequestProperty("Authorization", "Basic TUNTREVNMDAwNF9NT0JJTEVQT1JUQUxTRVRSSUFMMDAwNERFVl9NT0JJTEVfQU5PTllNT1VTX0FQUElEOlJvNDBieXVfaG53dHZw");
                adapter.addRequestProperty("Oracle-Mobile-Backend-ID", "27b6e049-ddd9-4d81-9258-958113629a14");
                adapter.addRequestProperty("Content-Type", "application/json");
                String appId = "com.company.UkougMobileApp";
                String os = DeviceManagerFactory.getDeviceManager().getOs().equalsIgnoreCase("IOS") ? "IOS" : "ANDROID";
                String payload =
                    "{\"notificationToken\": \""+id+"\",\"mobileClient\": {\"id\": \"" + appId +
                    "\",\"version\": \"1.0\",\"platform\": \""+os+"\"}}";
                try {
                    ValueExpression ve2 =
                        AdfmfJavaUtilities.getValueExpression("#{applicationScope.registerDevicePayload}", String.class);
                    ve2.setValue(AdfmfJavaUtilities.getAdfELContext(), payload);
                    
                    String result = adapter.send(payload);
                    ValueExpression ve3 =
                        AdfmfJavaUtilities.getValueExpression("#{applicationScope.registerDeviceReturnPayload}", String.class);
                    ve3.setValue(AdfmfJavaUtilities.getAdfELContext(), result);
                } catch (Exception e) {
                    ValueExpression ve3 =
                        AdfmfJavaUtilities.getValueExpression("#{applicationScope.registerDeviceReturnPayload}", String.class);
                    ve3.setValue(AdfmfJavaUtilities.getAdfELContext(), e.getLocalizedMessage());
                    throw new AdfException(e.getLocalizedMessage(), AdfException.ERROR);
                }
            }

    
}