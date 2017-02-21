package org.wso2.carbon.extension.analytics.receiver.stomp.internal.util;

import org.wso2.carbon.event.input.adapter.core.InputEventAdapterConfiguration;

/**
 * Created by vsarris on 1/26/17.
 */
public class StompConnectionConfiguration {
    private String username = null;
    private String password = null;
    private String hostName;
    private String topic;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public StompConnectionConfiguration(InputEventAdapterConfiguration eventAdapterConfiguration) {
        this.username = eventAdapterConfiguration.getProperties().get(StompReceiverEventAdapterConstants.STOMP_SERVER_USERNAME);
        this.password = eventAdapterConfiguration.getProperties().get(StompReceiverEventAdapterConstants.STOMP_SERVER_PASSWORD);
        this.hostName = eventAdapterConfiguration.getProperties().get(StompReceiverEventAdapterConstants.STOMP_SERVER_URL);
        this.topic = eventAdapterConfiguration.getProperties().get(StompReceiverEventAdapterConstants.STOMP_SERVER_TOPIC);
    }
}
