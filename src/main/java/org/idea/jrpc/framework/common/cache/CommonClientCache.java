package org.idea.jrpc.framework.common.cache;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import org.idea.jrpc.framework.core.common.RpcInvocation;

/**
 * @description: 公用缓存，存储请求队列等公共消息
 */
public class CommonClientCache {
    public static BlockingQueue<RpcInvocation> SEND_QUEUE = new ArrayBlockingQueue<>(100);
    public static Map<String,Object> RESP_MAP = new ConcurrentHashMap<>();
}
