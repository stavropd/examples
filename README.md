Design Overview
================

The java program "example2" consists of 3 components in order to get the daily summary of New York for 2017/10/30 and output a file with some metrics.

The 1st component makes the RESTfull call to the given URL and gets the response as a string. This component is implemented by the "ApiInvoker" class in "my.example.rest" package.

The 2nd component reads the given response string and finds the required metrics in the Json response object. The metrics whose values should be reported are given as "Metric" objects. These object also contain the name of the metrics and a short description as given in table 2. This component is implemented by the "ResponseHandler" class in "my.example.response" package.

The 3rd component takes a list of metrics as the result of the 2nd component and creates an output file with those metrics. This compoent is implemented by "FileEmitter" class in "my.example.output" package.

All the required functionality is implemented in "my.example.Main" class. To run the program you can just execute the following command "java -jar example2-1.0-SNAPSHOT.jar".
