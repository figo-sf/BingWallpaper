package com.figo.bing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String desc;
    private String date;
    private String url;

    @Override
    public String toString() {
        String smallUrl = url + "&pid=hp&w=384&h=216&rs=1&c=4";
        return String.format("![%s](%s)%s [download 4k](%s)",desc, smallUrl, date, url);
    }

    public String formatMarkdown() {
        return String.format("%s | [%s](%s) ", date, desc, url);
    }

    public String toLarge() {
        String smallUrl = url + "&w=1000";
        return String.format("![](%s)Today: [%s](%s)", smallUrl, desc, url);
    }

}
