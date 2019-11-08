package com.sy.rabbitmq.pubsub.direct;

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
        channel.exchangeDeclare(ExchangeNameConstant.DIRECT_EXCHANGE_NAME, BuiltinExchangeType.DIRECT, false);
        //此时向交换机发送消息
        /**
         * 第一个参数：交换机的名字
         * 第二个参数：路由键，发送消息的时候对应的路由键
         */
        channel.basicPublish(ExchangeNameConstant.DIRECT_EXCHANGE_NAME, "select", null, "direct消息".getBytes());
        channel.close();
        connection.close();
    }
}
