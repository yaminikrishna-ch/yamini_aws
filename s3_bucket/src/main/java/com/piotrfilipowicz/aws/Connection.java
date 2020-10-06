package com.piotrfilipowicz.aws;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.http.HttpMethodName;
import com.piotrfilipowicz.aws.ApiGatewayResponse;
import com.piotrfilipowicz.aws.JsonApiGatewayCaller;

public class Connection {
	//private String AWS_API_GATEWAY_ENPOINT;
	static final String exampleJsonRequest= "{\n" +
            "  \"type\": \"fileUrl\",\n" +
            "  \"status\": 200\n" +
            "}";
	public void ConnectionToAPI(String fileUrl ,String AWS_IAM_ACCESS_KEY, String AWS_IAM_SECRET_ACCESS_KEY, String AWS_REGION, String AWS_API_GATEWAY_ENPOINT) {
		try {
			
			//this.AWS_API_GATEWAY_ENPOINT = AWS_API_GATEWAY_ENPOINT;
            JsonApiGatewayCaller caller = new JsonApiGatewayCaller(
                    AWS_IAM_ACCESS_KEY,
                    AWS_IAM_SECRET_ACCESS_KEY,
                    null,
                    AWS_REGION,
                    new URI(AWS_API_GATEWAY_ENPOINT)
            );

            ApiGatewayResponse response = caller.execute(HttpMethodName.POST, "/s3api", new ByteArrayInputStream(exampleJsonRequest.getBytes()));

            System.out.println(response.getBody());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
	}

}
