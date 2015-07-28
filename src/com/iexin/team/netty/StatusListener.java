package com.iexin.team.netty;

/**
 * 
 * @author lzx
 * 
 */

public interface StatusListener {
	void channelConnected(PushSocket pushSocket);

	void channelClosed(PushSocket pushSocket);
}
