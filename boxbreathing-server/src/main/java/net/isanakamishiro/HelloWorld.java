package net.isanakamishiro;

import spark.servlet.SparkApplication;

import static spark.Spark.get;

public class HelloWorld implements SparkApplication {
    @Override
    public void init() {

        get("/", (request, response)
                -> "Hello from SparkJava running on GAE Standard Java8 runtime2.");

        get("/hello/:name", (request, response) -> {
            return "SparkJava running on GAE Java8 says: Hello XXX: " + request.params(":name");
        });
    }

    // Use Servlet annotation to define the Spark filter without web.xml:
//    @WebFilter(
//            filterName = "SparkInitFilter", urlPatterns = {"/*"},
//            initParams = {
//                    @WebInitParam(name = "applicationClass", value = "HelloWorld")
//            })
//    public static class SparkInitFilter extends spark.servlet.SparkFilter {
//    }

    public static void main(String[] args) {
        new HelloWorld().init();
    }
}
