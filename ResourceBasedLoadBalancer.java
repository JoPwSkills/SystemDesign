// Implementation of Resource Base LB
import java.util.*;

public class Main {
    public static void main(String[] args) {
      ResourceBasedLoadBalancer loadBalancer = new ResourceBasedLoadBalancer();
      
      // Create servers and add them to the Map
      Server server1 = new Server("Server 1");
      Server server2 = new Server("Server 2");
      Server server3 = new Server("Server 3");
      
      loadBalancer.addServer(server1);
      loadBalancer.addServer(server2);
      loadBalancer.addServer(server3);
      
      server1.updateCpuLoad(30.0);
      server2.updateCpuLoad(50.0);
      server3.updateCpuLoad(20.0);
      
      for(int i=0; i<5; i++){
        loadBalancer.handleRequest(i);
      }
      
  }
}

class Server {
  String name;
  
  double cpuLoad; // It represents the current CPU Load percentage of the Server
  
  public Server(String name) {
    this.name = name;
    this.cpuLoad = 0.0;
  }
  
  // Simulate updating the CPU Load for the Server
  
  public void updateCpuLoad(double load) {
    this.cpuLoad = load;
  }
  
  public double getCpuLoad() {
    return this.cpuLoad;
  }
  
  public String getName() {
    return this.name;
  }
}

class ResourceBasedLoadBalancer {
  private Map<String, Server> servers;
  
  public ResourceBasedLoadBalancer() {
    this.servers = new HashMap<>();
  }
  
  // Adds a server to the load balancer
  public void addServer(Server server) {
    servers.put(server.getName(), server);
  }
  
  // Find the server with the lowest CPU Load and assign the request to It
  
  public Server getServerWithMostResources() {
    Server bestServer = null;
    double lowestLoad = Double.MAX_VALUE;
    
    for(Server server: servers.values()){
      if(server.getCpuLoad() < lowestLoad) {
        lowestLoad = server.getCpuLoad();
        bestServer = server;
      }
    }
    
    return bestServer;
  }
  
  // Simulate handling a request
  public void handleRequest(int i) {
    Server bestServer = getServerWithMostResources();
    
    if(bestServer != null) {
      System.out.println("Routing request:"+ (i+1)  +" to server: " + bestServer.getName() + " with current CPU load: " + bestServer.getCpuLoad() + "%");
      
      // Increment the cpu load with 5%
      bestServer.updateCpuLoad(bestServer.getCpuLoad() + 5.0);
    }
    else {
      System.out.println("No servers available");
    }
  }
  
  
}
