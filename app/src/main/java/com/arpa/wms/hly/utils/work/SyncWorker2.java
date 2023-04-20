package com.arpa.wms.hly.utils.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import androidx.work.rxjava3.RxWorker;

import io.reactivex.rxjava3.core.Single;

public class SyncWorker2 extends RxWorker {

    private static final String TAG = SyncWorker2.class.getSimpleName();

    public static final String KEY_STRING_DATA = "string_data";

    private static final int COMPRESS_MAX_SIZE = 200;
    private static final int PROGRESS_MAX = 100;

    private Context context;

    // private FileUploader uploader;


    public SyncWorker2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        // ApiService apiService = ServiceBuilder.buildService(ApiService.class);
        // uploader = new FileUploader(apiService);
    }

    @NonNull
    @Override
    public Single<Result> createWork() {
        /*Data data = getInputData();

        String strData = data.getString(KEY_STRING_DATA);

        List<String> stringList = deserializeFromJson(strData);

        List<File> files = new ArrayList<>();

        for (String path : stringList) {
            File f = new File(path);
            files.add(f);
        }

        return Single.fromObservable(
                Luban.compress(context, files)
                        .setMaxSize(COMPRESS_MAX_SIZE)
                        .putGear(Luban.CUSTOM_GEAR)
                        .setCompressFormat(Bitmap.CompressFormat.JPEG)
                        .asListObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<List<File>, ArrayList<String>>() {
                            @Override
                            public ArrayList<String> apply(List<File> files) throws Exception {

                                ArrayList<String> filListPath = new ArrayList<>();

                                for (File file : files) {
                                    filListPath.add(file.getAbsolutePath());
                                }
                                return filListPath;
                            }
                        })
                        .map(new Function<ArrayList<String>, Disposable>() {
                            @Override
                            public Disposable apply(ArrayList<String> strings) throws Exception {
                                HashMap<String, RequestBody> map = new HashMap<>();
                                return getUploadObserver(map, strings);
                            }
                        }).map(new Function<Disposable, Result>() {
                                   @Override
                                   public Result apply(Disposable disposable) throws Exception {
                                       return Result.success();
                                   }
                               }
                        )
        );*/

        return null;
    }

    /*private Disposable getUploadObserver(HashMap<String, RequestBody> map, ArrayList<String> files) {
        return uploader.uploadMultiImage(map, files)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Double>() {
                    @Override
                    public void accept(Double progress) throws Exception {
                        notifyUpload((int) (100 * progress));
                        Log.d(TAG, "accept: " + (int) (100 * progress));
                    }
                });
    }

    public void notifyUpload(int progress) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Config.NOTIFICATION_CHANNEL);
        builder.setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Upload")
                .setContentText("Uploading in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setAutoCancel(true);

        if (progress < PROGRESS_MAX) {
            builder.setProgress(PROGRESS_MAX, progress, false);
        } else {
            builder.setContentText("Upload complete")
                    .setProgress(0, 0, false);
        }
        notificationManagerCompat.notify(200, builder.build());
    }

    public static List<String> deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        Type listOf = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(jsonString, listOf);
    }*/
}
