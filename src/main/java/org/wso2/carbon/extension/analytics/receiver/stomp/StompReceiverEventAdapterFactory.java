/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.wso2.carbon.extension.analytics.receiver.stomp;

import org.wso2.carbon.event.input.adapter.core.*;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.util.StompReceiverEventAdapterConstants;

import java.util.*;

public class StompReceiverEventAdapterFactory extends InputEventAdapterFactory {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Resources", Locale.getDefault());

    /**
     * This method returns the receiver type as a String.
     *
     * @return
     */
    @Override
    public String getType() {
        return StompReceiverEventAdapterConstants.ADAPTER_TYPE_STOMP;
    }

    /**
     * Specify supported message formats for the created receiver type.
     *
     * @return
     */
    @Override
    public List<String> getSupportedMessageFormats() {
        List<String> supportInputMessageTypes = new ArrayList<>();
        supportInputMessageTypes.add(MessageType.XML);
        supportInputMessageTypes.add(MessageType.JSON);
        supportInputMessageTypes.add(MessageType.TEXT);
        return supportInputMessageTypes;
    }

    /**
     * Here the properties have to be defined for the receiver.
     * When defining properties you can implement to configure property values from the management console.
     *
     * @return
     */
    @Override
    public List<Property> getPropertyList() {
        ArrayList<Property> propertyList = new ArrayList<>();
        Property hostNameProperty = new Property(StompReceiverEventAdapterConstants.STOMP_SERVER_URL);
        hostNameProperty.setDisplayName(
                resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_URL));
        hostNameProperty.setRequired(true);
        hostNameProperty.setHint(resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_URL_HINT));
        propertyList.add(hostNameProperty);


        // Username
        Property usernameProperty = new Property(StompReceiverEventAdapterConstants.STOMP_SERVER_USERNAME);
        usernameProperty.setDisplayName(
                resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_USERNAME));
        usernameProperty.setRequired(true);
        usernameProperty.setHint(resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_USERNAME_HINT));
        propertyList.add(usernameProperty);

        // Password
        Property passwordProperty = new Property(StompReceiverEventAdapterConstants.STOMP_SERVER_PASSWORD);
        passwordProperty.setDisplayName(
                resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_PASSWORD));
        passwordProperty.setRequired(true);
        passwordProperty.setSecured(true);
        passwordProperty.setHint(resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_PASSWORD_HINT));
        propertyList.add(passwordProperty);

        // TOPIC
        Property topicProperty = new Property(StompReceiverEventAdapterConstants.STOMP_SERVER_TOPIC);
        topicProperty.setDisplayName(
                resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_TOPIC));
        topicProperty.setRequired(true);
        topicProperty.setHint(resourceBundle.getString(StompReceiverEventAdapterConstants.STOMP_SERVER_TOPIC_HINT));
        propertyList.add(topicProperty);



        return propertyList;
    }

    /**
     * Specify any hints to be displayed in the management console.
     *
     * @return
     */
    @Override
    public String getUsageTips() {
        return null;
    }

    /**
     * This method creates the receiver by specifying event adapter configuration
     * and global properties which are common to every adapter type.
     *
     * @param inputEventAdapterConfiguration
     * @param map
     * @return
     */
    @Override
    public InputEventAdapter createEventAdapter(InputEventAdapterConfiguration inputEventAdapterConfiguration, Map<String, String> map) {
        return new StompReceiverEventAdapter(inputEventAdapterConfiguration,map);
    }
}