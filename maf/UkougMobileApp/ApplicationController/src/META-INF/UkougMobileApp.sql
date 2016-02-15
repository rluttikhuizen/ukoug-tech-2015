



CREATE TABLE SPEAKERS 
(
      COMPANY VARCHAR ,
      FIRSTNAME VARCHAR ,
      ID NUMERIC  NOT NULL,
      LASTNAME VARCHAR ,
      BIO VARCHAR ,
    CONSTRAINT SPEAKERS_PK PRIMARY KEY(ID)
);
CREATE TABLE SESSIONS 
(
      ABSTRACT1 VARCHAR ,
      CONTENT_LEVEL NUMERIC ,
      DAY VARCHAR ,
      END_TIME VARCHAR ,
      EXPERIENCE VARCHAR ,
      HALL VARCHAR ,
      ID NUMERIC  NOT NULL,
      LENGTH NUMERIC ,
      PREFERRED_LENGTH VARCHAR ,
      PRESENTATION_FORMAT VARCHAR ,
      PRESENTATION_STATUS VARCHAR ,
      SESSION_DATE VARCHAR ,
      START_TIME VARCHAR ,
      STREAM VARCHAR ,
      TITLE VARCHAR ,
      TOPIC VARCHAR ,
      TRACK VARCHAR ,
      SPEAKER_ID NUMERIC ,
      SPEAKER_NAME VARCHAR ,
    CONSTRAINT SESSIONS_PK PRIMARY KEY(ID)
);
CREATE TABLE ATTENDANCE
(
      ID NUMERIC  NOT NULL,
      STATUS VARCHAR ,
      ATTENDEE_ID NUMERIC ,
      SESSION_ID NUMERIC ,
      NAME VARCHAR ,
      USERNAME VARCHAR ,
      COMPANY VARCHAR ,
      PRESENT VARCHAR ,
      RATING VARCHAR ,
    CONSTRAINT ATTENDANCE_PK PRIMARY KEY(ID)
);

CREATE TABLE DATA_SYNCH_ACTIONS 
(
      ID NUMERIC NOT NULL,
      SERVICE_CLASS_NAME VARCHAR NOT NULL,
      ENTITY_CLASS_NAME VARCHAR NOT NULL,
      JSON_PAYLOAD VARCHAR ,
      ACTION VARCHAR NOT NULL,
      DATE_CREATED DATE NOT NULL,
      DATE_LAST_SYNCH DATE NOT NULL,
      LAST_SYNCH_ERROR VARCHAR,
      CUSTOM_METHOD_NAME VARCHAR,
    CONSTRAINT DSA_PK PRIMARY KEY(SERVICE_CLASS_NAME ,ID)
);

CREATE TABLE WEB_SERVICE_CALL 
(
      ID NUMERIC  NOT NULL,
      CONNECTION VARCHAR ,
      REQUEST VARCHAR ,
      METHOD VARCHAR ,
      REQUEST_HEADERS VARCHAR ,
      DURATION NUMERIC ,
      REQUEST_PAYLOAD VARCHAR ,
      RESPONSE_PAYLOAD VARCHAR ,
      ERROR_MESSAGE VARCHAR ,
      TIMESTAMP DATE ,
    CONSTRAINT WEB_SERVICE_CALL_PK PRIMARY KEY(ID)
);