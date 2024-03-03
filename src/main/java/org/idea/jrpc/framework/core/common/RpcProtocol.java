package org.idea.jrpc.framework.core.common;

import java.io.Serializable;
import java.util.Arrays;

import static org.idea.jrpc.framework.core.common.constants.RpcConstants.MAGIC_NUMBER;

public class RpcProtocol implements Serializable{
    private static final long serialVersionUID = 5359096060555795690L;
    
    // Security checking
    private short magicNumber = MAGIC_NUMBER;
    // Server will check if the content length is over the limit
    // If it is bigger than the limit, server would not read the content
    private int contentLength;
    // Core content
    private byte[] content;

    public RpcProtocol(byte[] content) {
        this.content = content;
        this.contentLength = content.length;
    }


    public short getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(short magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RpcProtocol{" +
                "contentLength=" + contentLength +
                ", content=" + Arrays.toString(content) +
                '}';
    }

}
