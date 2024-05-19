package org.srwcrw.random;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainPrintWorksScanner {


    private static final String ticketURLString = "https://bookings.printworkslondon.co.uk/book/16178/ticket";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ticketURLString)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
            .thenAccept(System.out::println).join();
    }

}
