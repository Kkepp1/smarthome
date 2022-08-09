
#include <WiFiUdp.h>
#include <NTPClient.h>


#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <WiFiManager.h>

#include <Servo.h>
#include <DHT.h>

#include <ArduinoJson.h>
#include <FirebaseESP8266.h>
#include <ESP8266WiFi.h>

Servo servo;
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP,"2.asia.pool.ntp.org",7*3600);

#define chandht 4
#define loaidht DHT11
DHT dht(chandht,loaidht);

#define den1 14
#define den2 12
#define den3 13

#define FIREBASE_HOST "my-application-fa381-default-rtdb.firebaseio.com/"
#define FIREBASE_AUTH "4VjAKP8HiD6WEnjHoVut9X93c3NzU7GTwvP0ZrfQ"

FirebaseData firebaseData;
FirebaseJson json;

float nhietdo,pre_nhietdo=0;
float doam,pre_doam=0;
String path = "/";
String a,pre_a=" ";
String b,pre_b=" ";
String c,pre_c=" ";
String d,pre_d=" ";
int t=0;
int check_connect=1;
int hour,minute;
int lhour,lmin,lshour,lsmin;
int khour,kmin,kshour,ksmin;
int bhour,bmin,bshour,bsmin;

void configModeCallback (WiFiManager *myWiFiManager)
{
  Serial.println("Entered config mode");
  Serial.println(WiFi.softAPIP());
  Serial.println(myWiFiManager->getConfigPortalSSID());
}


void setup() {
  
  Serial.begin(9600);
  pinMode(16,OUTPUT);
  pinMode(den1,OUTPUT);
  pinMode(den2,OUTPUT);
  pinMode(den3,OUTPUT);

  // servo
  servo.attach(5);
  servo.write(90);
  // sensor begin
    dht.begin();
  // start to connect to wifi
    WiFiManager wifiManager;
    wifiManager.autoConnect("config wifi_esp", "123456789");
//      wifiManager.setAPCallback(configModeCallback);
//      if (!wifiManager.autoConnect())
//      {
//        Serial.println("failed to connect and hit timeout");
//        ESP.reset();
//        delay(1000);
//      }
      Serial.println("connected...yeey :)");
  // start to connect to firebase
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
    Firebase.reconnectWiFi(true);
    if(!Firebase.beginStream(firebaseData, path))
    {
    Serial.println("reason: " + firebaseData.errorReason());
    Serial.println();
    }
     Firebase.setFloat(firebaseData, path + "/living",0);
     Firebase.setFloat(firebaseData, path + "/kitchen",0);
     Firebase.setFloat(firebaseData, path + "/bedroom",0);
     Firebase.setFloat(firebaseData, path + "/door",0);
     t=millis();
     timeClient.begin();
     digitalWrite(16,LOW);
     
    //timeClient.setTimeOffset(25200);    
}

void loop() {
  // put your main code here, to run repeatedly
  if(millis()-t>1000){
      
      timeClient.update();
      Serial.println(timeClient.getFormattedTime());
      hour=timeClient.getHours();
      minute=timeClient.getMinutes();
      nhietdo = dht.readTemperature();
      doam = dht.readHumidity();
      Serial.println(nhietdo);
      if(nhietdo!=pre_nhietdo){
        Firebase.setFloat(firebaseData, path + "/temp",nhietdo);
        pre_nhietdo=nhietdo;
      }
      if(doam!=pre_doam){
         Firebase.setFloat(firebaseData, path + "/hum",doam);
         pre_doam=doam;
      }
      Firebase.setFloat(firebaseData, path + "/connect",check_connect);
      check_connect=-check_connect;
      t=millis();
    }
  //--------------------------------- control ---------------------------------------
  if(Firebase.getString(firebaseData, path + "door"))   d= firebaseData.stringData();
  if(d!=pre_d){
      if (d =="1") {
    Serial.println("door ON");
    servo.write(180);
    }else{
     servo.write(90);
      } 
    pre_d=d;
    //delay(10);
    }
  if(Firebase.getString(firebaseData, path + "kitchen"))   a= firebaseData.stringData();
  if(a!=pre_a){
    if (a =="1") {
      Serial.println("den1 ON");
      digitalWrite(den1, HIGH);
    }else{
      Serial.println("den1 Of");
      digitalWrite(den1, LOW);
      } 
    pre_a=a;
    delay(10);
    }

   if(Firebase.getString(firebaseData, path + "living"))   b= firebaseData.stringData();
   if(b!=pre_b){
      if (b =="1") {
      Serial.println("den2 ON");
      digitalWrite(den2, HIGH);
       
    }else{
      Serial.println("den1 Of");
      digitalWrite(den2, LOW);
      
      } 
    pre_b=b;
    delay(10);
    }
    if(Firebase.getString(firebaseData, path + "bedroom"))   c= firebaseData.stringData();
    if(c!=pre_c){
        if (c =="1") {
    Serial.println("den3 ON");
    digitalWrite(den3, HIGH);
     
    
    }else{
      Serial.println("den1 Of");
      digitalWrite(den3, LOW);
      
      } 
      pre_c=c;
      delay(10);
    }



    // ------------------------------alarm---------------------------------------------------------------
    // living
    if(Firebase.getInt(firebaseData, path + "l_hour"))   lhour= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "l_min"))   lmin= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "l_shour"))   lshour= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "l_smin"))   lsmin= firebaseData.intData();
    if(hour==lhour&&minute==lmin){
        digitalWrite(den2,HIGH);
        b="1";
        Firebase.setFloat(firebaseData, path + "/living",1);
        Firebase.setFloat(firebaseData, path + "/l_hour",-1);
        Firebase.setFloat(firebaseData, path + "/l_min",-1);
        delay(10);
    }else if(hour==lshour&&minute==lsmin){
        digitalWrite(den2,LOW);
        b="0";
        Firebase.setFloat(firebaseData, path + "/living",0);
        Firebase.setFloat(firebaseData, path + "/l_shour",-1);
        Firebase.setFloat(firebaseData, path + "/l_smin",-1);
        delay(10);
      }
   
    //kitchen
    if(Firebase.getInt(firebaseData, path + "k_hour"))   khour= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "k_min"))   kmin= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "k_shour"))   kshour= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "k_smin"))   ksmin= firebaseData.intData();
     if(hour==khour&&minute==kmin){
      a="1";
      digitalWrite(den1,HIGH);
      Firebase.setFloat(firebaseData, path + "/kitchen",1);
      Firebase.setFloat(firebaseData, path + "/k_hour",-1);
       Firebase.setFloat(firebaseData, path + "/k_min",-1);
        delay(10);
    }else if(hour==kshour&&minute==ksmin){
      a="0";
      digitalWrite(den1,LOW);
      Firebase.setFloat(firebaseData, path + "/kitchen",0);
      Firebase.setFloat(firebaseData, path + "/k_shour",-1);
      Firebase.setFloat(firebaseData, path + "/k_smin",-1);
       delay(10);
      }
   
    //bedroom
    if(Firebase.getInt(firebaseData, path + "b_hour"))   bhour= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "b_min"))   bmin= firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "b_shour"))   bshour=firebaseData.intData();
    if(Firebase.getInt(firebaseData, path + "b_smin"))   bsmin= firebaseData.intData();
     if(hour==bhour&&minute==bmin){
      c="1";
      digitalWrite(den3,HIGH);
      Firebase.setFloat(firebaseData, path + "/bedroom",1);
        Firebase.setFloat(firebaseData, path + "/b_hour",-1);
       Firebase.setFloat(firebaseData, path + "/b_min",-1);
        delay(10);
    }else if(hour==bshour&&minute==bsmin){
      c="0";
      digitalWrite(den3,LOW);
      Firebase.setFloat(firebaseData, path + "/bedroom",0);
              Firebase.setFloat(firebaseData, path + "/b_shour",-1);
       Firebase.setFloat(firebaseData, path + "/b_smin",-1);
        delay(10);
      }
}
