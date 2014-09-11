/*
   Copyright 2012 Wyhw

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.elasticsearch.plugin.bx.rest.action;

import static org.elasticsearch.rest.RestRequest.Method.GET;
import static org.elasticsearch.rest.RestStatus.OK;
import static org.elasticsearch.rest.RestStatus.INTERNAL_SERVER_ERROR;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.indices.IndicesService;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.BytesRestResponse;


public class CustomStatusAction extends BaseRestHandler {
	private static String status = "";
    @Inject
    public CustomStatusAction(Settings settings, Client client,
            RestController controller, IndicesService indicesService) {
        super(settings, client);
        controller.registerHandler(GET, "/_customstatus", this);
    }


    @Override
    public void handleRequest(final RestRequest request,
            final RestChannel channel, final Client client) {

        String _status = request.param("status", null);

        try {
            // Wait for trigger
            if (_status != null) {
            	status = _status;
            }
            
            XContentBuilder builder = createXContentBuilderForReload(request);
            channel.sendResponse(new BytesRestResponse(OK, builder));
        } catch (Exception e) {
        	channel.sendResponse(new BytesRestResponse(INTERNAL_SERVER_ERROR));
        }
    }

    private XContentBuilder createXContentBuilderForReload(final RestRequest request) {
        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder();
            builder.startObject();
            builder.field("ok", "true");
            builder.field("status", status.length() == 0 ? "true" : status);
            builder.endObject();
        } catch (Exception e) {
        }
        return builder;
    }
}
