package cn.com.kxcomm.ipmitool.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class GobblerCommand {
	private Connection conn;
	private String ip;
	private String user;
	private String passwd;
	private String charset = Charset.defaultCharset().toString();
	private static final int TIME_OUT = 1000 * 5 * 60;

	public GobblerCommand(String ip, String user, String passwd) {
		super();
		this.ip = ip;
		this.user = user;
		this.passwd = passwd;
	}

	private boolean login() throws IOException {
		conn = new Connection(ip);
		conn.connect();
		return conn.authenticateWithPassword(user, passwd);
	}

	public String processStream(InputStream in, String charset)
			throws Exception {
		byte[] buf = new byte[1024];
		StringBuilder sb = new StringBuilder();
		while (in.read(buf) != -1) {
			sb.append(new String(buf, charset));
		}
		return sb.toString();

	}

	public String executeCommand(String cmds) {
		String result = "";
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		try {
			try {
				if (login()) {
					// Open a new {@link Session} on this connection
					Session session = conn.openSession();
					// Execute a command on the remote machine.
					session.execCommand(cmds);
					stdOut = new StreamGobbler(session.getStdout());
					outStr = processStream(stdOut, charset);
					stdErr = new StreamGobbler(session.getStderr());
					outErr = processStream(stdErr, charset);
					session.waitForCondition(ChannelCondition.EXIT_STATUS,
							TIME_OUT);
					if (outStr.length() > outErr.length()) {
						result = outStr;
					} else {
						result = outErr;
					}
					int a = session.getExitStatus();
				} else {
					throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			if (conn != null) {
				conn.close();
			}
			try {
				stdOut.close();
				stdErr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
