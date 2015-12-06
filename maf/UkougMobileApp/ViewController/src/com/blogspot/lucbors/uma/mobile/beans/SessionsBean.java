package com.blogspot.lucbors.uma.mobile.beans;

import com.blogspot.lucbors.uma.mobile.model.Attendance;

import javax.el.MethodExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.Model;

public class SessionsBean {
    public SessionsBean() {
        super();
    }

    public void autoRegistration(ActionEvent actionEvent) {
        MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.<>.execute}", Object.class, new Class[] {
                                                                     });


        me.invoke(AdfmfJavaUtilities.getELContext(), new Object[] { });
    }


    public void directRestCall() {

        String jsonResponse = "";

        RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();

        // Clear any previously set request properties, if any
        restServiceAdapter.clearRequestProperties();

        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.addRequestProperty("Authorization", "Basic TUNTREVNMDAwNF9NT0JJTEVQT1JUQUxTRVRSSUFMMDAwNERFVl9NT0JJTEVfQU5PTllNT1VTX0FQUElEOlJvNDBieXVfaG53dHZw");
        restServiceAdapter.addRequestProperty("Oracle-Mobile-Backend-ID", "27b6e049-ddd9-4d81-9258-958113629a14");

        // Set the connection name
        restServiceAdapter.setConnectionName("UkougRestConn");

        // Specify the type of request
        restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_GET);

        // Specify the number of retries
        restServiceAdapter.setRetryLimit(0);

        // Set the URI which is defined after the endpoint in the connections.xml.
        // The request is the endpoint + the URI being set

        restServiceAdapter.setRequestURI("/attendance");
        jsonResponse = "";

        // Execute SEND and RECEIVE operation
        try {
            // For GET request, there is no payload
            jsonResponse = restServiceAdapter.send("");

            // Now create a new Result object and parse the JSON string returned into this class
            Attendance att = new Attendance();
            att = (Attendance) JSONBeanSerializationHelper.fromJSON(Attendance.class, jsonResponse);
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


