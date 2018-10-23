/*
 * *
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.freeme.freemelite.dueros.devicemodule.sms.message;

import com.baidu.duer.dcs.util.message.Payload;

import java.util.List;

/**
 * Created by caoyushu01 on 2017/7/18.
 */

public class SendSmsByNamePayload extends Payload {
    private String messageContent = "";
    private List<CandidateRecipient> candidateRecipients;
    private String useSimIndex = "";
    private String useCarrier = "";

    public SendSmsByNamePayload () {

    }

    public String getMessageContent () {
        return messageContent;
    }

    public void setMessageContent (String messageContent) {
        this.messageContent = messageContent;
    }

    public List<CandidateRecipient> getCandidateRecipients () {
        return candidateRecipients;
    }

    public void setCandidateRecipients (List<CandidateRecipient> candidateRecipients) {
        this.candidateRecipients = candidateRecipients;
    }

    public String getUseSimIndex () {
        return useSimIndex;
    }

    public void setUseSimIndex (String useSimIndex) {
        this.useSimIndex = useSimIndex;
    }

    public String getUseCarrier () {
        return useCarrier;
    }

    public void setUseCarrier (String useCarrier) {
        this.useCarrier = useCarrier;
    }
}
