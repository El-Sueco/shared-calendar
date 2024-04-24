package at.home.sharedcalendar.service;

import java.net.URI;

public interface DbxClientService {

    URI startOAuthFlow(Boolean openBrowser);

    void finishOAuthFlow(String code);

    void resetOAuthInfo();

}