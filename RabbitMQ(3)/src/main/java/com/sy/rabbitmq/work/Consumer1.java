package com.sy.rabbitmq.work;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sy.constant.MQNameConstant;
import com.sy.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;


public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //开启能者多劳模式
        channel.basicQos(1);

        //创建队列
        channel.queueDeclare(MQNameConstant.WORK_QUEUE_NAME, false, false, true, null);
        //创建RMQ中的消费者对象，在消费者对象对应的回调函数中来处理消息得到以后要完成的操作
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //在这个方法中用来处理得到的消息
                System.out.println("消费方1收到了消息:" + new String(body));

                //如果将自动确认关闭，此时可以通过调用方法手动确认消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //消息达到消费方以后要进行确认（1）自动确认（2）手动确认
        //次处为true表示自动确认消息
        //此处为false表示手动确认消息
        channel.basicConsume(MQNameConstant.WORK_QUEUE_NAME, false, consumer);
    }
}
