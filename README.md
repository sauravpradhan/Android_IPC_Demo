# Android_IPC_Demo
A demo service with which an activity can bind and perform communication through messenger.

#How it is achieved?

We have two applications.
1)MyIpcActivity
2)MyService
Myservice is a Service which has Handler wrapped to Messenger implementation for IPC.
Any activity which wants to bind to it can bind with the service and on getting onServiceConnected() can cast the messenger's instance to Ibinder type.
Once the messenger instance is obtained now the activity can communicate with the service.

#How Service is communicating back with activity?
In MyIpcActivity's MainActivity we have a handler. We create a messenger and wrap that Messenger to Handler target. 
While sending message to service's handler we pass the IBinder instance through bundle.
While handling the messege sent from the activity, we decode the bundle to get the IBinder instance.
Now with this instance service can communicate with activity. I am just showing Toast message for Demo.

#How to use?
Just download the code and import to Eclipse as two separate applications.
Install the MyService and than install the MyIpcActivity. The Service must be running in the phone while launching MyIpcActivity and you'll see a Toast message showing Hello from Service and then another toast Hello from Activity.

