(function() {

  // Our region identifier
  var uuid = "0AC59CA4-DFA6-442C-8C65-22247851344C";
  var beaconRegion;

  function initialise() {
    // This is required because we only want to initialise the plugin's native code once
    cordova.plugins.locationManager.initialize();
    
    // Create the beacon region and setup the delegate
    beaconRegion = createRegion();
    var delegate = new cordova.plugins.locationManager.Delegate();

    // Callback for enter/exit region whilst monitoring
    delegate.didDetermineStateForRegion = function (pluginResult) {
      var options;
      if (pluginResult.state == "CLRegionStateInside") {
        // Fire local notification and start ranging from there so that ranging only
        // starts if the app is in the foreground, or the user taps on the notification
        options = {"alert":"Welcome! Thank you for entering the " + 
                     pluginResult.region.identifier + " region.", 
                   "sound":"SYSTEM_DEFAULT",
                   "vibration":"SYSTEM_DEFAULT",
                   "payload":{"id":pluginResult.region.identifier, "inside":true}};
        adf.mf.api.localnotification.add(options,  function() {}, function() {});
      } else if (pluginResult.state == "CLRegionStateOutside") {
        // Stop ranging immediately
        stopRanging();

        // Fire local notification
        options = {"alert":"Goodbye. Thank you for visiting the " + 
                     pluginResult.region.identifier + " region.",
                   "sound":"SYSTEM_DEFAULT",
                   "vibration":"SYSTEM_DEFAULT",
                   "payload":{"id":pluginResult.region.identifier, "inside":false}};
        adf.mf.api.localnotification.add(options,  function() {}, function() {});
      }
    };

    // Callback for ranging region
    delegate.didRangeBeaconsInRegion = function(pluginResult) {
      var beacons = pluginResult.beacons;
      for (var i = 0; i < beacons.length; i++) { 
        // If immediate to the beacon, present a message relevant to the beacon
        if (beacons[i].proximity == "ProximityImmediate") {
          var message = retrieveMessage(beacons[i]);
          adf.mf.api.setValue({"name": "#{applicationScope.message}", "value": message}, function() {}, function() {});
        }
      }
    };

    // Set delegate
    cordova.plugins.locationManager.setDelegate(delegate);

    // Required in iOS 8+
    cordova.plugins.locationManager.requestAlwaysAuthorization();

    // Start monitoring by default each time the app is launched
    startMonitoring();
  }

  function createRegion() {
    // Monitor any beacons in this UUID-based region
    return new cordova.plugins.locationManager.BeaconRegion("FakeBeacon", uuid); 
  }
  
  function retrieveMessage(beacon) {
    // TODO: A more real-life scenario would involve loading the messages relevant to each beacon
    // from an embedded file or by making a REST call to a server during app startup (which would 
    // provide the ability to update the beacon identifiers and the messages)
    return "You have approached a beacon with major value <b>" + beacon.major + "<\/b> and minor value <b>" + beacon.minor + 
           "<\/b>.  Relevant information could be provided from an embedded file or retrieved from a server.";
  }

  // Callable externally
  startMonitoring = function() {
    cordova.plugins.locationManager.startMonitoringForRegion(beaconRegion).fail(console.error).done();
    adf.mf.api.setValue({"name": "#{applicationScope.region}", "value": beaconRegion.uuid}, function() {}, function() {});
    adf.mf.api.setValue({"name": "#{applicationScope.monitoring}", "value": true}, function() {}, function() {});
  }

  // Callable externally
  stopMonitoring = function() {
    stopRanging(); // Must stop ranging first
    cordova.plugins.locationManager.stopMonitoringForRegion(beaconRegion).fail(console.error).done();
    adf.mf.api.setValue({"name": "#{applicationScope.monitoring}", "value": false}, function() {}, function() {});
  }

  // Callable externally
  startRanging = function() {
    cordova.plugins.locationManager.startRangingBeaconsInRegion(beaconRegion).fail(console.error).done();
    adf.mf.api.setValue({"name": "#{applicationScope.ranging}", "value": true}, function() {}, function() {});
  }

  // Callable externally
  stopRanging = function() {
    cordova.plugins.locationManager.stopRangingBeaconsInRegion(beaconRegion).fail(console.error).done();
    adf.mf.api.setValue({"name": "#{applicationScope.ranging}", "value": false}, function() {}, function() {});
    // Clear the message
    adf.mf.api.setValue({"name": "#{applicationScope.message}", "value": ""}, function() {}, function() {});
  }

  document.addEventListener("showpagecomplete", initialise, false);

}) ();