package com.sy.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.174.101");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
    }


    private RabbitMQUtils() {

    }

    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }


//    public static void main(String[] args) throws IOException, TimeoutException {
//        //先创建连接工厂
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("192.168.174.101");
//        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("123456");
//        //从连接工厂中获取连接对象
//        Connection connection = connectionFactory.newConnection();
//
//        //所有和RabbitMQ相关的操作都通过Channel来完成
//        Channel channel = connection.createChannel();
//        //举例：创建一个消息队列
//        /**
//         * 第一个参数：队列的名字
//         * 第二个参数：队列是否需要持久化
//         * 第三个参数：是否是排他队列
//         * 第四个参数:是否要自动删除
//         * 第五个参数：一些其它的队列的配置参数，例如何时删除队列，队列的有效期等
//         */
//        channel.queueDeclare("test_queue", false, false, true, null);
//
//        //关闭资源
//        //1.关闭Channel
//        channel.close();
//        //2.关闭Connection
//        connection.close();
//
//    }
}
