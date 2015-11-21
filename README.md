# ukoug-tech-2015

## Introduction
This repo contains the demo code for the Live Mobile and Cloud Hacking by ACEs session at UKOUG Tech 2015 Super Sunday. By making this demo code available you can execute (parts of the) Live Mobile and Cloud Hacking session yourself. You can find more info about this session on the [UKOUG Tech site] (http://www.supersunday15.ukoug.org/default.asp?p=14169&dlgact=shwprs&prs_prsid=11732&day_dayid=101). 
## Usecase
This demo is centered around building and deploying a Conference app that can be used by a conference and conference attendees to create sessions, (re)schedule sessions, register for sessions by attendees, evaluate sessions, etc.

## Video
Watch a [video] (https://community.oracle.com/docs/DOC-984188) about this session.

## Disclaimer
This code is meant for demo purposes only, and if used, should be treated accordingly.

## Contributors

* Ronald van Luttikhuizen
* Lonneke Dikmans
* Steven Davelaar
* Wilfred van der Deijl
* Luc Bors

## Contents of this repo

### Database
Contains a SQL script to create a database user "UKOUG_TECH_15" in an Oracle Database and create the necessary sequences and tables: "ATTENDEE", "SPEAKER", "PRESENTATION" and "ATTENDANCE". The SQL script also populates the tables with public data provided by the UKOUG Tech 2015 conference (speakers and sessions). Fictitious data is used to create demo data for the attendees and attendances. 

Note that while the speaker and session data is provided by UKOUG, it might be out-of-date. Use the UKOUG Tech 2015 conference site for up-to-date information about the conference.

### Java back-end
Contains a backend application providing the necessary functionality for the Conference app. There are two variants available for this backend application. Both variants use JPA/EclipseLink as persistency layer.

* SOAP Web Service
* REST Service

The SOAP Web Service backend is used to simulate an enterprise that wants to REST-enable its existing SOAP-based services. The REST Service is used to simulate a greenfield situation in which a RESTful service using JSON payloads is created right away.

The backend applications are deployed to WebLogic 12c, either JCS (PaaS) in the Oracle Cloud or using WebLogic 12c on-premise.

* Workspace: UKOUG_Tech_2015
* SOAP Web Service Project: ConferenceSoapWebService
* SOAP Web Service Test: ConferenceSoapWebService-soapui-project.xml (soapUI project in "Test" folder)
* REST Service Project: ConferenceRestService
* Prerequisites: Create a JDBC Data Source with JNDI name jdbc/ukougTech15 (see SQL script in "Database" folder)
