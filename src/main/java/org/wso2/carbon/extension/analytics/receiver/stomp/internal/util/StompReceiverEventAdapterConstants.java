/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.wso2.carbon.extension.analytics.receiver.stomp.internal.util;

public final class StompReceiverEventAdapterConstants {



    private StompReceiverEventAdapterConstants() {
    }
    public static final String ADAPTER_TYPE_STOMP = "stomp";
    public static final String STOMP_SERVER_URL = "stomp_url";
    public static final String STOMP_SERVER_URL_HINT = "stomp_url.hint";
    public static final String STOMP_SERVER_USERNAME = "username";
    public static final String STOMP_SERVER_USERNAME_HINT = "username.hint";
    public static final String STOMP_SERVER_PASSWORD = "password";
    public static final String STOMP_SERVER_PASSWORD_HINT = "password.hint";
    public static final String STOMP_SERVER_TOPIC = "topic";
    public static final String STOMP_SERVER_TOPIC_HINT = "topic.hint";
    public static final int DEFAULT_RETRY_INTERVAL = 30000 ;
    public static final int DEFAULT_RETRY_COUNT =3 ;
}
