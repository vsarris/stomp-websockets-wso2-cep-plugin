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

import org.wso2.carbon.event.input.adapter.core.InputEventAdapter;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterConfiguration;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterListener;
import org.wso2.carbon.event.input.adapter.core.exception.InputEventAdapterException;
import org.wso2.carbon.event.input.adapter.core.exception.InputEventAdapterRuntimeException;
import org.wso2.carbon.event.input.adapter.core.exception.TestConnectionNotSupportedException;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.util.StompAdapterListener;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.util.StompConnectionConfiguration;

import java.util.Map;
import java.util.UUID;

public class StompReceiverEventAdapter implements InputEventAdapter {
    /**
     * This method is called when initiating event receiver bundle.
     * Relevant code segments which are needed when loading OSGI bundle can be included in this method.
     *
     * @param inputEventAdapterListener
     * @throws InputEventAdapterException
     */

    private final InputEventAdapterConfiguration eventAdapterConfiguration;
    private StompAdapterListener stompAdapterListener;
    private final String id = UUID.randomUUID().toString();


    public StompReceiverEventAdapter(InputEventAdapterConfiguration eventAdapterConfiguration,
                                     Map<String, String> globalProperties){
        this.eventAdapterConfiguration = eventAdapterConfiguration;
    }
    @Override
    public void init(InputEventAdapterListener inputEventAdapterListener) throws InputEventAdapterException {
        StompConnectionConfiguration stompConnectionConfiguration;
        stompConnectionConfiguration = new StompConnectionConfiguration(eventAdapterConfiguration);
        stompAdapterListener = new StompAdapterListener(stompConnectionConfiguration,
                eventAdapterConfiguration,inputEventAdapterListener);
    }

    /**
     * This method checks whether the receiving server is available.
     *
     * @throws org.wso2.carbon.event.input.adapter.core.exception.TestConnectionNotSupportedException
     * @throws InputEventAdapterRuntimeException
     * @throws org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException
     */
    @Override
    public void testConnect() throws org.wso2.carbon.event.input.adapter.core.exception.TestConnectionNotSupportedException {
        throw new TestConnectionNotSupportedException("not-supported");
    }

    /**
     * This method will be called after calling the init() method.
     * Intention is to connect to a receiving end
     * and if it is not available "ConnectionUnavailableException" will be thrown.
     *
     * @throws InputEventAdapterRuntimeException
     * @throws org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException
     */
    @Override
    public void connect() throws InputEventAdapterRuntimeException, org.wso2.carbon.event.input.adapter.core.exception.ConnectionUnavailableException {
        stompAdapterListener.createConnection();
    }

    /**
     * This method can be called when it is needed to disconnect from the connected receiving server.
     */
    @Override
    public void disconnect() {
        if(stompAdapterListener!=null){
            stompAdapterListener.stopListener(eventAdapterConfiguration.getName());
        }

    }

    /**
     * The method can be called when removing an event receiver.
     * The cleanups that has to be done when removing the receiver can be done over here.
     */
    @Override
    public void destroy() {

    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof StompReceiverEventAdapter)) return false;
        StompReceiverEventAdapter that = (StompReceiverEventAdapter) object;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    /**
     * Returns a boolean output stating whether an event is duplicated in a cluster or not.
     * This can be used in clustered deployment.
     *
     * @return
     */
    @Override
    public boolean isEventDuplicatedInCluster() {
        return false;
    }

    /**
     * Checks whether events get accumulated at the adapter and clients connect to it to collect events.
     *
     * @return
     */
    @Override
    public boolean isPolling() {
        return false;
    }
}