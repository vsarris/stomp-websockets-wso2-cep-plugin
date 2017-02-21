# README #


### What is this repository for? ###


This is a plugin for wso2 CEP providing a receiver for stomp over websockets.

Currently stomp up to v1.2 is supported.

Tested with wso2 v4.2.0.

Works with both ws:// and wss://

### Dependencies ###

Other dependencies than wso2 ones are :

* TakahikoKawasaki/nv-websocket-client
* Andrejs Mivreņiks java stomp impl.


### How do I get set up? ###

* Package and drop the artifact generated inside the repository/components/dropins library of wso2
* Install the dependencies jars inside repository/components/lib folder. (nv-websocket-client-1.30.jar)
* Start the wso2 cep
