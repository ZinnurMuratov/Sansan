package sansan.ru.rockylabs.sansan.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import sansan.ru.rockylabs.sansan.MVP.models.dto.fcmDTO;
import sansan.ru.rockylabs.sansan.ui.activities.MainActivity;
import sansan.ru.rockylabs.sansan.utils.prefs.AuthPrefs;
import sansan.ru.rockylabs.sansan.utils.prefs.UserPrefs;

/**
 * Created by Zinnur on 22.12.16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(remoteMessage.getData());
            fcmDTO result = new Gson().fromJson(json,fcmDTO.class);
            if(userAuthorized() && result != null && UserPrefs.getUser() != null) {
                if (UserPrefs.getUser().getId().equals(result.getUserId())){
                    Log.d("rly? ", " "+ UserPrefs.getUser().getId().equals(result.getUserId()));
                    sendNotification( result.getTitle(), result.getBody());
                }

            }
        }
    }


    private void sendNotification(String title, String body) {

        Intent intent;
        intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent,  PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(Color.RED)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    private boolean userAuthorized() {
        return AuthPrefs.getToken() != null;
    }
}