package com.sy.rabbitmq.pubsub.fanout;

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
        channel.exchangeDeclare(ExchangeNameConstant.FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT, false);
        //此时向交换机发送消息
        /**
         * 第一个参数：交换机的名字
         */
        channel.basicPublish(ExchangeNameConstant.FANOUT_EXCHANGE_NAME, "", null, "fanout消息".getBytes());
        channel.close();
        connection.close();

    }
}
