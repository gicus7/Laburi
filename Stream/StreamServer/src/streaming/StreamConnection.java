package streaming;

import java.net.InetAddress;
public class StreamConnection {
    private String name;
    private InetAddress address;
    private int port;
    
    StreamConnection(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }
    
    String getName() {
        return name;
    }
    
    InetAddress getAddress() {
        return address;
    }
    
    int getPort() {
        return port;
    }
}
