package com.ludufre.plugins.screenshot;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Base64;
import android.view.View;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

@CapacitorPlugin(name = "Screenshot")
public class ScreenshotPlugin extends Plugin {
    @PluginMethod
    public void take(PluginCall call) {
        getBridge().getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int qualityLevel = 70;
                CompressFormat format = Bitmap.CompressFormat.JPEG;

                Bitmap img = null;
                View view = getBridge().getWebView();
                view.setDrawingCacheEnabled(true);
                img = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);

                Bitmap resizedImg = Bitmap.createScaledBitmap(img, (int)(img.getWidth()*0.3), (int)(img.getHeight()*0.3), true);

                String qualityString = call.getString("quality");
                String formatString = call.getString("format");

                if (qualityString != null) {
                    qualityLevel = Integer.parseInt(qualityString);
                    qualityLevel = Math.min(qualityLevel, 100);
                    qualityLevel = Math.max(qualityLevel, 0);
                }

                if (formatString != null) {
                    switch (formatString.toLowerCase(Locale.ROOT)) {
                        case "JPEG":
                            format = Bitmap.CompressFormat.JPEG;
                            break;
                        case "PNG":
                            format = Bitmap.CompressFormat.PNG;
                            break;
                        case "WEBP":
                            format = Bitmap.CompressFormat.WEBP_LOSSY;
                            break;
                    }
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                resizedImg.compress(format, qualityLevel, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                JSObject ret = new JSObject();
                ret.put("base64", new String(Base64.encode(byteArray, Base64.NO_WRAP)));
                call.resolve(ret);
            }
        });
    }
}
