package eu.senla.management.rest;

public record CookieRequest(
        String token,
        String cookie) {
}
