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
import oracle.ateam.sample.mobile.v2.persistence.manager.MCSPersistenceManager;

public class NativePushNotificationListener
  implements EventListener
{

  private static ADFMobileLogger sLog = ADFMobileLogger.createLogger(NativePushNotificationListener.class);


  public NativePushNotificationListener()
  {
  }


  public void onMessage(Event event)
  {
    String msg = event.getPayload();
    System.out.println("#### Message from the Server :" + msg);

    // Parse the payload of the push notification
    HashMap payload = null;
    String pushMsg = "No message received";
    try
    {
      payload = (HashMap) JSONBeanSerializationHelper.fromJSON(HashMap.class, msg);
      pushMsg = (String) payload.get("alert");
    }
    catch (Exception e)
    {
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

  public void onError(AdfException adfException)
  {
    System.out.println("#### Error: " + adfException.toString());
    // Write the error into app scope

    sLog.severe("#### Error: " + adfException.toString());

    AdfmfJavaUtilities.setELValue("#{applicationScope.errorMessage}", adfException.toString());

  }


  public void onOpen(String id)
  {
    AdfmfJavaUtilities.setELValue("#{applicationScope.deviceToken}", id);
    try
    {
      String result = new MCSPersistenceManager().registerDevice(id, "com.company.UkougMobileApp", "1.0");
      AdfmfJavaUtilities.setELValue("#{applicationScope.registerDeviceReturnPayload}", result);
    }
    catch (Exception e)
    {
      AdfmfJavaUtilities.setELValue("#{applicationScope.registerDeviceReturnPayload}", e.getLocalizedMessage());
      throw new AdfException(e.getLocalizedMessage(), AdfException.ERROR);
    }
  }


}
