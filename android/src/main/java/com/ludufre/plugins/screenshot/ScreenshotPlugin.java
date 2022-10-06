package com.ludufre.plugins.screenshot;

import android.graphics.Bitmap;
import android.util.Base64;
import android.view.View;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.ByteArrayOutputStream;

@CapacitorPlugin(name = "Screenshot")
public class ScreenshotPlugin extends Plugin {
    @PluginMethod
    public void take(PluginCall call) {
        getBridge().getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int qualityLevel = 50;
                Bitmap img = null;
                View view = getBridge().getWebView();
                view.setDrawingCacheEnabled(true);
                img = Bitmap.createBitmap(view.getDrawingCache());
                view.setDrawingCacheEnabled(false);

                Bitmap resizedImg = Bitmap.createScaledBitmap(img, (int)(img.getWidth()*0.4), (int)(img.getHeight()*0.4), true);

                String qualityString = call.getString("quality");
                if (qualityString != null) {
                    qualityLevel = Integer.parseInt(qualityString);
                    qualityLevel = Math.min(qualityLevel, 100);
                    qualityLevel = Math.max(qualityLevel, 0);
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                resizedImg.compress.compress(Bitmap.CompressFormat.JPEG, qualityLevel, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                JSObject ret = new JSObject();
                ret.put("base64", new String(Base64.encode(byteArray, Base64.NO_WRAP)));
                call.resolve(ret);
            }
        });
    }
}
