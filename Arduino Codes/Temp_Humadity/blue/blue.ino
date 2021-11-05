#include <dht.h>


#define dht_apin A0 // Analog Pin sensor is connected to
 
dht DHT;










void setup() {
Serial.begin(9600);
 //pinMode(8, OUTPUT); // put your setup code here, to run once:


  Serial.print("Press 1 For Humidity, 2 for Temperature\n\n");
  delay(1000);//Wait before accessing Sensor
 
 
 }

void loop() {

  
DHT.read11(dht_apin);


 

 if(Serial.available()>0)
   {     
      char data= Serial.read(); // reading the data received from the bluetooth module
      switch(data)
      {
        case '1': 
           
            Serial.print("Current humidity = ");
            Serial.print(DHT.humidity);
            Serial.print("%  ");
            print();
            break; 


             case '2': 
            
            
            Serial.print("temperature = ");
            Serial.print(DHT.temperature); 
            Serial.println("C  ");
            print();
            break; 



            
        default : break;
  


        
      }
      Serial.println(data);


      
   }

   

    
    delay(2000);
}


int print () {
  
  
  
   Serial.print("\n\nPress 1 For Humidity, 2 for Temperature\n\n");
  
  
  loop();
  }
