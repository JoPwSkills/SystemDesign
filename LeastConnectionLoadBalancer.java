// Implementation for Least Connection Method
import java.util.*;

public class Main {
    public static void main(String[] args) {
      // Create a Least Connection load balancer
      LeastConnectionLoadBalancer loadBalancer = new LeastConnectionLoadBalancer();
      
      // Add servers to the load balancer
      
      loadBalancer.addServer("Server 1");
      loadBalancer.addServer("Server 2");
      loadBalancer.addServer("Server 3");
      
      // Simulated requests and print the server to which each request is routed
      
      for(int i =0; i<10; i++){
        String selectedServer = loadBalancer.getServerWithLeastConnections();
        
        System.out.println("Request "+ (i+1) + ": Routed to " + selectedServer);
      }
  }
}

class LeastConnectionLoadBalancer {
  
  // serverName ---> 0
  private Map<String, Integer> serverConnections;
  
  public LeastConnectionLoadBalancer() {
    this.serverConnections = new HashMap<>();
  }
  
  public void addServer(String serverName) {
    // Add a server to the load balancer with 0 intial connection;
    serverConnections.put(serverName, 0);
  }
  
  public String getServerWithLeastConnections() {
    // Find the server with the least active connections
    
    int minConnections = Integer.MAX_VALUE;
    
    String selectedServer = null;
    
    for(Map.Entry<String, Integer> entry : serverConnections.entrySet()) {
      if(entry.getValue() < minConnections) {
        // [<serverName2, numberOfActiveConnections 10>, 
        // <serverName1, numberOfActiveConnections 5>]
        minConnections = entry.getValue(); // update the minimum
        selectedServer = entry.getKey();
      }
    }
    // Increment the connection count for the selected server
    
    if(selectedServer != null){
      serverConnections.put(selectedServer, minConnections + 1);
    }
    
    return selectedServer;
  }
  
 }
