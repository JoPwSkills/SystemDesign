// Source IP Hash Implementation
import java.util.*;

public class Main {
    public static void main(String[] args) {
      
      SourceIpHashLoadBalancer loadBalancer = new SourceIpHashLoadBalancer();
      
      loadBalancer.addServer("Server 1");
      loadBalancer.addServer("Server 2");
      loadBalancer.addServer("Server 3");
      
      String[] sourceIps = {"192.168.1.", "10.0.0.1", "172.16.0.1", "192.168.1."};
      
      for(String sourceIp : sourceIps) {
        String selectedServer = loadBalancer.getServerForIp(sourceIp);
        System.out.println("Request from "+ sourceIp + "routed to " + selectedServer);
      }
       
  }
}

class SourceIpHashLoadBalancer {
  private Map<String, String> ipToServerMap;
  
  public SourceIpHashLoadBalancer() {
    this.ipToServerMap = new HashMap<>();
  }
  
  public void addServer(String serverName) {
    // Add server to the mapping
    ipToServerMap.put(serverName, serverName);
  }
  
  public String getServerForIp(String sourceIp) {
    // Calculated hash of the sourceIp
    int hash = sourceIp.hashCode();
    
    System.out.println("SourceIp: "+ sourceIp+ " hash value: " + hash);
    // Get the list of available servers
    String[] servers = ipToServerMap.keySet().toArray(new String[0]);
    
    // Map the hash value to a server index
    int serverIndex = Math.abs(hash) % servers.length;
    
    // Return the selected server;
    return servers[serverIndex];
  }
  
}

