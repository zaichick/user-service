package com.zaichick.user.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ParameterStore {

    public DbCredentials getDbCredentials() {
        GetParametersResult parametersResult = buildClient().getParameters(buildParameterStoreRequest());

        try {
            return new ObjectMapper().readValue(parametersResult.getParameters().get(0).getValue(), DbCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private GetParametersRequest buildParameterStoreRequest() {

        return new GetParametersRequest()
                .withNames("database-zaichiktest-credential\n")
                .withWithDecryption(true);
    }

    private AWSSimpleSystemsManagement buildClient() {

        AWSSimpleSystemsManagementClientBuilder builder = AWSSimpleSystemsManagementClientBuilder.standard()
                .withRegion(Regions.EU_WEST_2);

//        if (account != null) {
            builder.withCredentials(new ProfileCredentialsProvider("personal"));
//        }

        return builder.build();
    }
}
