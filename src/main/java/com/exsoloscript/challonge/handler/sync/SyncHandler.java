package com.exsoloscript.challonge.handler.sync;

import com.exsoloscript.challonge.handler.error.ErrorHandler;
import com.exsoloscript.challonge.util.ErrorUtil;
import com.google.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public abstract class SyncHandler {

    @Inject
    private ErrorUtil errorUtil;

    public <T> Response<T> handleResponse(Call<T> call) throws IOException {
        Response<T> response = call.execute();

        if (response.isSuccessful()) {
            return response;
        } else {
            ErrorHandler.handleError(this.errorUtil.parseError(response));
            return response;
        }
    }
}