package BrazilCenter.Tcp.Client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Timer;
import java.util.TimerTask;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpClient {
	private Bootstrap bootstrap = new Bootstrap();
	private SocketAddress addr_;
	private Channel channel_;
	private Timer timer_;

	public TcpClient(String host, int port, Timer timer) {
		this(new InetSocketAddress(host, port), timer);
	}

	/** start the connectin. */
	public void RunClient() {
		bootstrap.group(new NioEventLoopGroup());
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(createNMMessageHandler());
			}
		});

		scheduleConnect(10);
	}

	/** constructor. */
	public TcpClient(SocketAddress addr, Timer timer) {
		this.addr_ = addr;
		this.timer_ = timer;
	}

	public boolean send(String msg) {
		if (channel_ != null && channel_.isActive()) {
			ByteBuf buf = channel_.alloc().buffer().writeBytes(msg.getBytes());
			channel_.writeAndFlush(buf);
		} else {
			// throw new IOException("Can not send message to inactive
			// connection");
			return false;
		}
		return true;
	}

	public void close() {
		try {
			channel_.close().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void doConnect() {
		try {
			ChannelFuture f = bootstrap.connect(addr_);
			f.addListener(new ChannelFutureListener() {
				public void operationComplete(ChannelFuture future) throws Exception {
					if (!future.isSuccess()) {// if is not successful, reconnect
						future.channel().close();
						bootstrap.connect(addr_).addListener(this);
					} else {// good, the connection is ok
						channel_ = future.channel();
						// add a listener to detect the connection lost
						addCloseDetectListener(channel_);
						connectionEstablished();
					}
				}

				private void addCloseDetectListener(Channel channel) {
					// if the channel connection is lost, the
					// ChannelFutureListener.operationComplete() will be called
					channel.closeFuture().addListener(new ChannelFutureListener() {
						public void operationComplete(ChannelFuture future) throws Exception {
							connectionLost();
							scheduleConnect(5);
						}

					});

				}
			});
		} catch (Exception ex) {
			scheduleConnect(1000);

		}
	}

	private void scheduleConnect(long millis) {
		timer_.schedule(new TimerTask() {
			@Override
			public void run() {
				doConnect();
			}
		}, millis);
	}

	private ChannelHandler createNMMessageHandler() {
		return new ChannelHandlerAdapter() {
			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) {
				ByteBuf buf = (ByteBuf) msg;
				int n = buf.readableBytes();
				if (n > 0) {
					byte[] b = new byte[n];
					buf.readBytes(b);
					handleMessage(new String(b));
				}
			}
		};
	}

	public void handleMessage(String msg) {
		System.out.println(msg);

	}

	public void connectionLost() {
		LogUtils.logger.info("disconnect to the tcp server. ");
	}

	public void connectionEstablished() {

	}

	public static void main(String... args) {
		TcpClient conn = new TcpClient("127.0.0.1", 9999, new Timer());
	}
}
