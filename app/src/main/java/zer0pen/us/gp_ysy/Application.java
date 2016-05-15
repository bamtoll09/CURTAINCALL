package zer0pen.us.gp_ysy;

import com.orm.SugarContext;

/**
 * Created by 박재현 on 2016-05-15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(getApplicationContext());
    }
}
