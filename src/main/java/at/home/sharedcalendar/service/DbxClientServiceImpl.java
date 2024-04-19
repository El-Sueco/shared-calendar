package at.home.sharedcalendar.service;

import at.home.sharedcalendar.repository.DbxClientRepository;
import at.home.sharedcalendar.repository.model.DbxClientModel;
import com.dropbox.core.*;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class DbxClientServiceImpl implements DbxClientService {

    @Value("${dropbox.auth.key}")
    private String DBX_KEY;

    @Value("${dropbox.auth.secret}")
    private String DBX_SECRET;

    @Inject
    private DbxClientRepository dbxClientRepository;

    @Override
    public URI startOAuthFlow(Boolean openBrowser) {
        DbxWebAuth webAuth = getDbxWebAuth();
        DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
                .withNoRedirect()
                .withTokenAccessType(TokenAccessType.OFFLINE)
                .build();

        URI authUrl = null;
        try {
            authUrl = new URI(webAuth.authorize(webAuthRequest));
            if (openBrowser && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(authUrl);
                } catch (IOException ignored) {
                }
            }
        } catch (URISyntaxException ignored) {
        }
        return authUrl;
    }

    @Override
    public void finishOAuthFlow(String code) {
        try {
            DbxAuthFinish dbxAuthFinish = getDbxWebAuth().finishFromCode(code.trim());
            DbxClientModel dbxClientModel = new DbxClientModel(DBX_KEY, DBX_SECRET, dbxAuthFinish.getRefreshToken());
            dbxClientRepository.save(dbxClientModel);
        } catch (DbxException ignored) {
        }
    }

    @Override
    public void resetOAuthInfo() {
        // TODO for future invalidate token
    }

    void uploadFile(String calendarName, InputStream icalInputStream) {
        DbxClientModel dbxClientModel = dbxClientRepository.findByClientKeyAndClientSecret(DBX_KEY, DBX_SECRET).orElseThrow(UnsupportedOperationException::new);
        DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("at.home/shared-calendar");
        DbxCredential dbxCredential = new DbxCredential("", 0L, dbxClientModel.getRefreshToken(), DBX_KEY, DBX_SECRET);
        DbxClientV2 dbxClientV2 = new DbxClientV2(dbxRequestConfig, dbxCredential);

        try {
            FileMetadata metadata = dbxClientV2.files()
                    .uploadBuilder("/" + calendarName + ".ics")
                    .withMode(WriteMode.OVERWRITE)
                    .uploadAndFinish(icalInputStream);
        } catch (IOException | DbxException ignored) {
        }
    }

    private DbxWebAuth getDbxWebAuth() {
        DbxAppInfo dbxAppInfo = new DbxAppInfo(DBX_KEY, DBX_SECRET);
        DbxRequestConfig requestConfig = new DbxRequestConfig("at.home/shared-calendar");
        return new DbxWebAuth(requestConfig, dbxAppInfo);
    }
}