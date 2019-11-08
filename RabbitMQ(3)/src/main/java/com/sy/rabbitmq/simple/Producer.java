package com.sy.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sy.constant.MQNameConstant;
import com.sy.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(MQNameConstant.SIMPLE_QUEUE_NAME, false, false, false, null);
        /**
         * 第一个参数：交换机名，simple模式下没有交换机
         * 第二个参数：路由键，在simple模式下就是队列的名字
         * 第四个参数:具体要发送的消息
         */
        for (int i = 1; i <= 10; i++) {
            channel.basicPublish("", MQNameConstant.SIMPLE_QUEUE_NAME, null, ("simple模式中发送消息" + i).getBytes());
            Thread.sleep(500);
        }

        channel.close();
        connection.close();
    }
}
