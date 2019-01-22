package com.gmail.tarasov1998.wfu2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

class GetLocation {

    private String cities;

    Location getLocation(String data) {
        Location location = new Location();
        HTTPGet httpGet = new HTTPGet();
        Document doc = null;
        try
        {
            doc = Jsoup.connect(httpGet.getLocationData(data)).get();

        } catch (
                IOException e)
        {
            e.printStackTrace();
        }
        return location;
    }
}
