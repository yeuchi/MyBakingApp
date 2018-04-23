package com.ctyeung.mybakingapp.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by ctyeung on 3/6/18.
 */
import com.ctyeung.mybakingapp.utility.JSONHelper;

import org.json.JSONObject;

import java.net.URL;

public class Step extends StepDetail
{
    public static final String KEY_SHORT_DESC = "shortDescription";
    public static final String KEY_DESC = "description";
    public static final String KEY_VIDEO_URL = "videoURL";
    public static final String KEY_THUMB_URL = "thumbnailUrl";

    public Step(String str,
                Context context)
    {
        super(str, context);
    }

    public Step(JSONObject json)
    {
        super(json);
    }

    public String getShortDescription()
    {
        return JSONHelper.parseValueByKey(mJson, KEY_SHORT_DESC);
    }

    public String getDescription()
    {
        return JSONHelper.parseValueByKey(mJson, KEY_DESC);
    }

    public Uri getVideoUri()
    {
        Uri uri = parseVideoUri(KEY_VIDEO_URL);
        if (null!=uri)
            return uri;

        // check thumbnail if it has uri
        uri = parseVideoUri(KEY_THUMB_URL);
        return uri;
    }

    private Uri parseVideoUri(String key)
    {
        String str = JSONHelper.parseValueByKey(mJson, key);
        if(null!=str) {

            String checkString = str.toLowerCase();
            if (checkString.contains(".jpg") ||
                checkString.contains(".jpeg")||
                    checkString.contains(".png") ||
                    checkString.contains(".gif"))
                return null;
        }

        Uri uri = (null==str || 0==str.length())?
                    null:
                    Uri.parse(str);

        return uri;
    }

    public Uri getThumbnailUri()
    {
        String str = JSONHelper.parseValueByKey(mJson, KEY_THUMB_URL);

        if(null!=str) {
            String checkString = str.toLowerCase();

            if (checkString.contains(".jpg") ||
                    checkString.contains(".jpeg")||
                    checkString.contains(".png") ||
                    checkString.contains(".gif"))
                return Uri.parse(str);
        }

        return null;
    }

    public String getJSONString()
    {
        return mJson.toString();
    }
}
