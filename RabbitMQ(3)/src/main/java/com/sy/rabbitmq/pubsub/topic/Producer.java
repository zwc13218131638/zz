package com.sy.rabbitmq.pubsub.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sy.constant.ExchangeNameConstant;
import com.sy.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //定义交换机
        channel.exchangeDeclare(ExchangeNameConstant.TOPIC_EXCHANGE_NAME, BuiltinExchangeType.TOPIC, false);
        //此时向交换机发送消息
        /**
         * 第一个参数：交换机的名字
         * 第二个参数：在topic模式中允许通过  . 进行分割的路由键的形式
         */
        channel.basicPublish(ExchangeNameConstant.TOPIC_EXCHANGE_NAME, "item.update.product", null, "topic消息".getBytes());
        channel.close();
        connection.close();

    }
}
