package org.idea.jrpc.framework.common.config;

public class ClientConfig {
    private Integer port;

    private String serverAddr;
    
    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort() {
        this.port = port;
    }
}
