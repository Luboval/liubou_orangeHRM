package eu.senla.management.auth;

public record CookieRequest(
        String token,
        String cookie) {
}
