package com.lzxmy.demo.aidl;
interface IMyService
{
   String getValue();
   void answerRingingCall();
boolean enableDataConnectivity();
boolean disableDataConnectivity();
boolean isDataConnectivityPossible();
}
