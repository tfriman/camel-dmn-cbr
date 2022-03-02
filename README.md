# Example project how externalise content based routing decision.

This project contains two applications, one is a [Camel REST App](./camel-cbr) and another 
is a [Decision Service REST app](./dmn-cbr) done with Kogito DMN. 

Camel app gets the 'order' in and uses order data fields to ask decision service whether or not the order is eligible for premium or just plain standard handling. 

Tested with OpenShift 4.9.

[MIT License](LICENSE.txt)