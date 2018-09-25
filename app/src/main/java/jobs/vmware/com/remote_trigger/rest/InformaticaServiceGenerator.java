package jobs.vmware.com.remote_trigger.rest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import jobs.vmware.com.remote_trigger.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static jobs.vmware.com.remote_trigger.NetworkConstants.BASE_URL_PEOPLECONNECT;

/**
 * Created by amedishetti on 9/25/2018.
 */

public class InformaticaServiceGenerator {

    private static OkHttpClient okHttpClient;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    public static final Executor THREAD_POOL_EXECUTOR
            = Executors.newFixedThreadPool(CORE_POOL_SIZE);

    public static Retrofit getRestClient() {


        return new Retrofit.Builder()
                .baseUrl(BASE_URL_PEOPLECONNECT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
