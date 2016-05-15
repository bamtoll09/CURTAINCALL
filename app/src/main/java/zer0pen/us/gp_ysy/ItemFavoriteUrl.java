package zer0pen.us.gp_ysy;

import com.orm.SugarRecord;

/**
 * Created by 박재현 on 2016-05-15.
 */
public class ItemFavoriteUrl extends SugarRecord {

    String title;
    String url;

    public ItemFavoriteUrl() { }

    public ItemFavoriteUrl(String title, String url) {
        this.title = title;
        this.url = url;
    }

    /*public String getTitle() {
        return title;
    }

    public String getUrl() {
        return Url;
    }*/
}
