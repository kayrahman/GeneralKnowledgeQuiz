package neel.com.quizdoor.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static neel.com.quizdoor.Data.DatabaseContracts.QuizInfoContract.TABLE_NAME_QUIZ_INFO;

public class QuizInfoContentProvider extends ContentProvider {


    QuizInfoDbHelper mQuizInfoDbHelper;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        mQuizInfoDbHelper = new QuizInfoDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = mQuizInfoDbHelper.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        // int match = sUriMatcher.match(uri);
        Cursor retCursor =  db.query(TABLE_NAME_QUIZ_INFO,
                null,
                null,
                null,
                null,
                null,
                null);



        // Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the desired Cursor
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
