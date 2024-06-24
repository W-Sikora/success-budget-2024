package pl.wsikora.successbudget.v3.common.util;

public class Constants {

    public static final String USER_ROLE = "USER";
    public static final String STATIC_RESOURCES_PATH = "/static/**";
    public static final String WEB_INF_RESOURCES_PATH = "/WEB-INF/**";
    public static final String USERNAME_PARAMETER = "username";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String LANDING_PAGE_PATH = "/";
    public static final String REGISTRATION_PATH = "/registration";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_FAILURE_PATH = LOGIN_URL + "?invalid=true";

    private Constants() {}
}
