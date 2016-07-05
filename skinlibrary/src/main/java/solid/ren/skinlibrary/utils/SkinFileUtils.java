package solid.ren.skinlibrary.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import solid.ren.skinlibrary.config.SkinConfig;

/**
 * Created by _SOLID
 * Date:2016/7/5
 * Time:10:17
 */
public class SkinFileUtils {

    public static String copySkinAssertToDir(Context context, String name, String toDir) {
        String toFile = toDir + File.separator + name;
        try {
            InputStream is = context.getAssets().open(name);
            File fileDir = new File(toDir + File.separator + SkinConfig.SKIN_DIR_NAME);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            OutputStream os = new FileOutputStream(toFile);
            int byteCount;
            byte[] bytes = new byte[1024];

            while ((byteCount = is.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
            os.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return toFile;
    }

    public static String getSkinDir(Context context) {
        File skinDir = new File(getCacheDir(context), "skin");
        if (skinDir.exists()) {
            skinDir.mkdirs();
        }
        return skinDir.getAbsolutePath();
    }

    /**
     * 得到手机的缓存目录
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir.getAbsolutePath();
            }
        }

        File cacheDir = context.getCacheDir();

        return cacheDir.getAbsolutePath();
    }

}