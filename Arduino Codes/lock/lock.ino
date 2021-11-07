#include <EEPROM.h>

int word_address =0;

void setup()
{
  
 
  Serial.begin(9600);
  Serial.println("Setup OK");
  
}
void loop()
{
  String inByte="";
  if (Serial.available() > 0) {
    // get incoming byte:
    inByte = Serial.read();
    Serial.println(inByte);
if (inByte =="1") {

  lockWrite(9);
 // Serial.println("OK1");
  }

  if (inByte =="2") {

    lockRead();
   //  Serial.println("OK2");
  
  }

  



  }
  
  }









  void lockWrite (int a)
  {
    
    
    EEPROM.write(word_address, a);
    }


int lockRead ()
{
  
  
  Serial.println(EEPROM.read(word_address));
  
  return EEPROM.read(word_address);
  }
   
