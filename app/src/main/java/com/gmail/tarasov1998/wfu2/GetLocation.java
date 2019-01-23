package com.gmail.tarasov1998.wfu2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class GetLocation {

    private String cities;

    static Location getLocation(String data) {
        Location location = new Location();
        Document doc = null;
        Elements table = null;
        Element id = null;

        doc = Jsoup.parse(data);
        table = doc.getElementsByClass("table");
        id = doc.getElementById("forecast-list");


        return location;
    }

}
