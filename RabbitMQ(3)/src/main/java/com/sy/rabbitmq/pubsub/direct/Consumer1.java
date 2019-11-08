package com.sy.rabbitmq.pubsub.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sy.constant.ExchangeNameConstant;
import com.sy.constant.MQNameConstant;
import com.sy.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;


public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(MQNameConstant.DIRECT_QUEUE_NAME1, false, false, true, null);
        //将队列和交换机绑定在一起
        /**
         * 第一个参数：队列名
         * 第二个参数：交换机名
         * 第三个参数：是队列上面的绑定键
         */
        channel.queueBind(MQNameConstant.DIRECT_QUEUE_NAME1, ExchangeNameConstant.DIRECT_EXCHANGE_NAME, "insert");

        //创建RMQ中的消费者对象，在消费者对象对应的回调函数中来处理消息得到以后要完成的操作
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //在这个方法中用来处理得到的消息
                System.out.println("insert消费者收到了消息:" + new String(body));
            }
        };

        //消息达到消费方以后要进行确认（1）自动确认（2）手动确认
        //次处为true表示自动确认消息
        channel.basicConsume(MQNameConstant.DIRECT_QUEUE_NAME1, true, consumer);
    }
}
