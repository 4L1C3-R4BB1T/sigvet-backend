package br.com.sigvet.api.application.constant;

public final class FilterConstants {

    public static final String LIMIT_KEY = "limit";
    public static final String PAGE_KEY = "page";
    public static final String SORT_KEY = "sort";
    public static final String DEFAULT_SORT = "";
    public static final Integer DEFAULT_LIMIT = 10;
    public static final Integer DEFAULT_PAGE = 0;
    public static final Integer MAX_LIMIT = 100;
    public static final String EQUAL_FILTERS_KEY = "equal_filters";
    public static final String DEFAULT_EQUAL_FILTERS = null;
    public static final String IN_FILTERS_KEY = "in_filters";
    public static final String DATE_FILTERS_KEY = "date_filters";
    public static final String DEFAULT_DATE_FILTERS = "date_filters";
    public static final String DEFAULT_IN_FILTERS = null;

    public static final String DOT = ".";

    private FilterConstants() {}

}
