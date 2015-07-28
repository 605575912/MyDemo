package com.lzxmy.demo.utils;

/**
 * 接口动作标识
 * 
 * @author Administrator
 * 
 */

public interface LBSAction {
	static final int RECORD_NO = 0x000; // 不在录音
	static final int RECORD_ING = 0x011; // 正在录音
	static final int RECODE_END = 0x013; // 完成录音

	static final int VOICE_PLAYING = 0x000; // 正在播放
	static final int VOICE_PLAYED = 0x001; // 播放过
	static final int VOICE_UNPLAYED = 0x002; // 未播放过
	// 消息类型 0.全部 1.邮件 2.加好友消息 3.系统消息 4.群聊 5.line聊天
	static final int groupchat = 0x004; //
	static final int chat = 0x005; //

	static final int SENDING = 0x001; // 文本发送
	static final int OK = 0x003; // 下载完成
	static final int FAIL = 0x004; // 发送文字 或接收文件失败 如果是自己则是发送失败，否则为文件下载失败
	static final int AGAIN_FAIL = 0x005; // 循环 发送或接收数据失败，需手动或者下次启动执行
	static final int FILEUP_FAIL = 0x007; // 发送文件失败
	static final int FILE_UPING = 0x008; // 文件上传
	static final int FILE_DOWNING = 0x009; // 文件下载
	static final int DELETE = 0x010; // 删除
	/**
	 * 消息到达马上入库
	 */
	static final int MESSAGEARRIVE = 0x011; // 消息到达

	static final int CHAT_MSG_VOICE = 0x002; // 语音
	static final int CHAT_MSG_TEXT = 0x000; // 文字
	static final int CHAT_MSG_IMAGE = 0x001; // 图片
	static final int O_CHAT_MSG_VOICE = 0x003; // 语音
	static final int O_CHAT_MSG_TEXT = 0x004; // 文字
	static final int O_CHAT_MSG_IMAGE = 0x005; // 图片
}
