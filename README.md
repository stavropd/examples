Design Overview
================

The java program "example2" is implemented based on 3 interfaces which define the requested behaviour. Our goal is to get the daily summary of New York for 2017/10/30 and output a file with some metrics. The interfaces are described below.

The 1st interface(ApiInvokerInt) defines the communication with the api service. This interface is implemented by the "ApiInvoker" class in "my.example.rest" package. This class makes the RESTfull call to the given URL and gets the response as a string.

The 2nd interface(ResponseHandlerInt) defines the way that the response from the api service will be handled in order to read the required metrics. This interface is implemented by the "ResponseHandler" class in "my.example.response" package. This class reads the given response string and finds the required metrics in the Json response object. The metrics whose values should be reported are given as "Metric" objects. These objects also contain the name of the metrics and a short description as given in table 2.

The 3rd interface (EmitterInt) defines the way to emit the metrics in an output. This inteface is implemented by "FileEmitter" class in "my.example.output" package. This class takes a list of metrics as the result of the 2nd component and creates an output file with those metrics.

All the logic of the application is implemented by the DailySummarizer class and the method "getDailySumAndEmitToFile".
This DailySummarizer class is called in "my.example.Main" class which is defined as the executable class. To run the program you can just execute the following command "java -jar example2-1.0-SNAPSHOT.jar" and the "Main" class will be called.
