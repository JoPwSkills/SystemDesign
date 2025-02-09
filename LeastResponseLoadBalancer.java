// Implementation of Least Response time LB Algo

import java.util.*;

public class Main {
    public static void main(String[] args) {
      LeastResponseLoadBalancer loadBalancer = new LeastResponseLoadBalancer();
      
      loadBalancer.addServer("Server USA 1 ", 11L);
      loadBalancer.addServer("Server USA 2", 10L);
      loadBalancer.addServer("Server USA 3", 9L);
        
      for(int i=0; i<10; i++){
        String selectedServer  = loadBalancer.getServerWithLeastResponseTime();
        
        System.out.println("Request " + (i+1) + " : Routed to  "+ selectedServer);
      }
  }
}


class LeastResponseLoadBalancer{
  private Map<String, Long> serverResponseTimes;
  
  public LeastResponseLoadBalancer() {
    this.serverResponseTimes = new HashMap<>();
  }
  
  public void addServer(String serverName, Long responsTime) {
    // Add a server to the load balancer with 0 initial resposne time;
    serverResponseTimes.put(serverName, responsTime);
  }
  
  public String getServerWithLeastResponseTime() {
    // Find the server with least accumulated response time
    
    long minResponseTime = Long.MAX_VALUE;
    
    String selectedServer = null;
    
    for(Map.Entry<String, Long> entry: serverResponseTimes.entrySet()) {
      if(entry.getValue() < minResponseTime) {
        minResponseTime = entry.getValue();
        selectedServer = entry.getKey();
      }
    }
    
    // Increment the response time for the selectedServer
    if(selectedServer!=null) {
      serverResponseTimes.put(selectedServer, minResponseTime + 1);
    }
    
    return selectedServer;
  }
}
