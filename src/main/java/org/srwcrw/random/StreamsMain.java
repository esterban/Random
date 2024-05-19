package org.srwcrw.random;

import java.util.List;

public class StreamsMain {


//20230304,1,1M,10
//            20230304,2,1M,20
//            20230304,2,1M,20

    public static void main(String[] args) {
        List<String> csvFile = List.of("20230304,1,1M,10", "20230304,2,1M,20", "20230304,3,1M,30");

        String asOfDate = "20230304";
        String mnemonic = "1M";

        long maxPerf = csvFile.stream().map(e -> e.split(","))
                .filter(e -> e[0].equals(asOfDate) && e[2].equals(mnemonic))
                .map(e -> Integer.valueOf(e[3]))
                .reduce(0, (a,b) -> b > a ? b : a);

        System.out.println("Max = " + maxPerf);
    }
}
