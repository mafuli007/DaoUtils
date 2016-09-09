package BrazilCenter.Tcp.Server;

import BrazilCenter.DaoUtils.Utils.LogUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TcpServer {
	
	private int port;
	public TcpServer(int port) {
		this.port = port;

	}

	public void start(final ServerHandler handler) {

		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		try {

			ServerBootstrap bootstrap = new ServerBootstrap();

			bootstrap.group(boss, worker);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.option(ChannelOption.SO_BACKLOG, 1024);  
			bootstrap.option(ChannelOption.TCP_NODELAY, true);  
			bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);  
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline p = socketChannel.pipeline();
					p.addLast(handler);
				}				
			});
			ChannelFuture f = bootstrap.bind(port).sync();
			if (f.isSuccess()) {
				LogUtils.logger.debug("Start Server:" + this.port);
			}
			//close the connection.
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			LogUtils.logger.error("Failed to start server: " + e.getMessage());
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		TcpServer server = new TcpServer(9889);
		server.start(new ServerHandler());
	}
}
