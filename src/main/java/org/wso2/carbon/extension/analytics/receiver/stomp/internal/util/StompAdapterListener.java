package org.wso2.carbon.extension.analytics.receiver.stomp.internal.util;

import com.neovisionaries.ws.client.WebSocketException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterConfiguration;
import org.wso2.carbon.event.input.adapter.core.InputEventAdapterListener;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.stomp.StompClient;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.stomp.StompMessageListener;
import org.wso2.carbon.extension.analytics.receiver.stomp.internal.stomp.StompSubscription;

import java.io.IOException;

/**
 * Created by vsarris on 1/25/17.
 */
public class StompAdapterListener implements Runnable{


    private static final Log log = LogFactory.getLog(StompAdapterListener.class);
    private StompConnectionConfiguration stompConnectionConfiguration;
    private InputEventAdapterListener eventAdapterListener = null;

    private final int STATE_STOPPED = 0;
    private final int STATE_STARTED;
    private int workerState;
    private int tenantId;
    private int retryInterval = StompReceiverEventAdapterConstants.DEFAULT_RETRY_INTERVAL;
    private int retryCountMax = StompReceiverEventAdapterConstants.DEFAULT_RETRY_COUNT;
    private boolean connectionSucceeded = false; // maybe for connection re-try
    private String adapterName;

    private Object lock = new Object();
    private  StompClient stompClient = null;

    public StompAdapterListener(StompConnectionConfiguration stompConnectionConfiguration,
                                InputEventAdapterConfiguration eventAdapterConfiguration,
                                InputEventAdapterListener inputEventAdapterListener) {
        this.stompConnectionConfiguration = stompConnectionConfiguration;

        this.eventAdapterListener = inputEventAdapterListener;
        this.adapterName = eventAdapterConfiguration.getName();

        this.tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        workerState = STATE_STOPPED;
        STATE_STARTED = 1;


    }

    /**
     * Start the thread of adapter
     */
    public void createConnection() {
        new Thread(this).start();
    }


    @Override
    public void run() {


        try {
            workerState = STATE_STARTED;

            log.info("Initializing StompListener");


            try {
                stompClient = new StompClient(stompConnectionConfiguration.getHostName());
                stompClient.connect(stompConnectionConfiguration.getUsername(),stompConnectionConfiguration.getPassword());
                StompSubscription stompSubscription = stompClient.subscribe(stompConnectionConfiguration.getTopic(), new StompMessageListener() {

                    @Override
                    public void onMessage(String message) {
                        log.info("Stomp receiver"+message);
                        //addition for cep
                        try{
                            PrivilegedCarbonContext.startTenantFlow();
                            PrivilegedCarbonContext.getThreadLocalCarbonContext().setTenantId(tenantId);
                            eventAdapterListener.onEvent(message);
                        }finally {
                            PrivilegedCarbonContext.endTenantFlow();
                        }

                    }
                });

            } catch (WebSocketException | IOException e) {
                log.error("Error in stomp receiver"+e.getMessage());
            }


        }catch (Exception e){
            log.error("Error in stomp receiver"+e.getMessage());
            workerState = STATE_STOPPED;
        }



    }

    public void stopListener(String adapterName) {
        log.info("Closing stomp listener for "+adapterName);
        try{
            stompClient.disconnect();
            workerState = STATE_STOPPED;
            log.info("Stomp connection closed for receiver " + adapterName);
        }catch (Exception e){
            log.error("Stomp connection close error for receiver " + adapterName);
        }



    }





}
