package com.blogspot.lucbors.uma.application;

import java.util.HashMap;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.event.NativeLocalNotificationEvent;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class NativeLocalNotificationListener implements EventListener {

  public NativeLocalNotificationListener() {
  }

  public void onMessage(Event event) {
    try {
      HashMap<String, Object> payloadMap = ((NativeLocalNotificationEvent)event).getPayloadObject();
      JSONObject jsonPayload = (JSONObject)payloadMap.get("payload");
      boolean inside = jsonPayload.getBoolean("inside");
    
      // If inside, start ranging and navigate to the Welcome feature
      if (inside) {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction("Sessions", "startRanging", new Object[] {});
        AdfmfContainerUtilities.gotoFeature("Sessions");
        
        
          // Now open the Sessions Feature and navigate to the appropiate sessionregistration.
          // The session Id should be part of the pushmessage's payoad
          // 
          
          /*
          
          String sessionId = pushMsg = (String)payload.get("sessionId");
          ValueExpression ve =
          AdfmfJavaUtilities.getValueExpression("#{applicationScope.sessionId}", String.class);
          
          ve.setValue(AdfmfJavaUtilities.getAdfELContext(), sessionId);
          
          
          //and navigate (perhaps this ust be transferred to Sessions feature.
          AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                            "adf.mf.api.amx.doNavigation", new Object[] {
                                                                            "registerForSession" });
          */
     
        
        
        
        
      }
    } catch (JSONException e) {
      System.out.println("Local Notification JSON error: " + e.getMessage());
    }
  }

  public void onError(AdfException adfException) {
    System.out.println("Local Notification error: " + adfException.getMessage());
  }

  public void onOpen(String token) {
    System.out.println("Local Notification opened: " + token);
  }
}