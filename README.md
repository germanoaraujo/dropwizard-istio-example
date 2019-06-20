# Dropwizard Sandbox App

Sample Dropwizard application uses Istio for [canary deployment](https://istio.io/blog/2017/0.1-canary/). For information on Dropwizard please visit http://www.dropwizard.io/.

## How To Run

To run the application locally, enter the root directory of the application using the command line and type:

```
mvn clean package
java -jar target/dropwizard-sandbox-*.jar server local.yml
``` 

The application will run on http://localhost:8080, and an admin page can be found on  http://localhost:8081
