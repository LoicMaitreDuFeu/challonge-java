package com.exsoloscript.challonge.handler.call;

import com.exsoloscript.challonge.handler.retrofit.RetrofitMatchHandler;
import com.exsoloscript.challonge.handler.retrofit.RetrofitServiceProvider;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.enumeration.MatchState;
import com.exsoloscript.challonge.model.exception.ChallongeException;
import com.exsoloscript.challonge.model.query.MatchQuery;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.List;

@Singleton
public class MatchHandler {

    private RetrofitMatchHandler matchHandler;
    private ChallongeApiCallFactory factory;

    @Inject
    MatchHandler(RetrofitServiceProvider provider, ChallongeApiCallFactory factory) {
        this.matchHandler = provider.createService(RetrofitMatchHandler.class);
        this.factory = factory;
    }

    public ChallongeApiCall<List<Match>> getMatches(String tournament, int participantId, MatchState state) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.matchHandler.getMatches(tournament, participantId, state));
    }

    public ChallongeApiCall<Match> getMatch(String tournament, int matchId, boolean includeAttachments) throws IOException, ChallongeException {
        return this.factory.createApiCall(matchHandler.getMatch(tournament, matchId, includeAttachments ? 1 : 0));
    }

    public ChallongeApiCall<Match> updateMatch(String tournament, int matchId, MatchQuery match) throws IOException, ChallongeException {
        return this.factory.createApiCall(this.matchHandler.updateMatch(tournament, matchId, match));
    }
}