package com.sy.rabbitmq.pull;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.GetResponse;
import com.sy.constant.MQNameConstant;
import com.sy.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PullMessage {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection con = RabbitMQUtils.getConnection();
        Channel channel = con.createChannel();
        GetResponse response = channel.basicGet(MQNameConstant.TOPIC_QUEUE_NAME, true);
        //如果response为空，说明消息已经全部消费完毕
        if (response == null) {
            System.out.println("没有可以消费的消息...");
        } else {
            System.out.println(new String(response.getBody()));
        }
        channel.close();
        con.close();
    }
}
