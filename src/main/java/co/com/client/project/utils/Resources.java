package co.com.client.project.utils;

public enum Resources {
    USERS("/api/users"),
    USERS_PAGE("/api/users?page=1"),
    USER_BY_ID("/api/users/{id}"),
    LOGIN("/api/login");

    private final String path;
    Resources(String path){ this.path = path; }
    public String path(){ return path; }
}
