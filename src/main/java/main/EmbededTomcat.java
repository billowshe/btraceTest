package main;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import servlet.HelloServlet;

public class EmbededTomcat {  
    private final Log log=LogFactory.getLog(getClass());  
    private static String CONTEXT_PATH = "/TestForTomcat7";  
    private static String PROJECT_PATH = System.getProperty("user.dir");  
    private static String WEB_APP_PATH = PROJECT_PATH + File.separatorChar + "src/main/java/servlet";  
    private static String CATALINA_HOME = PROJECT_PATH + "/Embedded/Tomcat";  
    private Tomcat tomcat = new Tomcat();  
    private int port;  
      
    public EmbededTomcat(int port){  
        this.port=port;  
    }  
      
    public void start()throws Exception{  
    	tomcat.setPort(port);  
        tomcat.setBaseDir(WEB_APP_PATH);  
        tomcat.getHost().setAutoDeploy(false);  
          
        String contextPath = "/book";  
        StandardContext context = new StandardContext();  
        context.setPath(contextPath);  
        tomcat.getHost().addChild(context);  
          
        tomcat.addServlet(contextPath, "homeServlet", new HelloServlet());  
        context.addServletMapping("/home", "homeServlet");  
        tomcat.start();   
        tomcat.getServer().await();  
        log.info("Tomcat started.");  
    }  
      
    public void stop()throws Exception{  
            try{  
                tomcat.stop();  
            }  
            catch(LifecycleException ex){  
                ex.printStackTrace();  
                log.error(ex.getMessage());  
                throw ex;  
            }  
            log.info("Tomcat stoped");  
    }  
          
    public void setPort(int port){  
        this.port=port;  
    }  
    public int getPort(){  
        return this.port;  
    }  
      
    public static void main(String[] args) throws Exception {  
        EmbededTomcat embededTomcat = new EmbededTomcat(8080);  
        embededTomcat.start();  
    }  
}  