package com.vmodev.common.helloworld.app.common;

/**
 * Created by Kien on 6/11/2014.
 */
public class Constant {

    public static final String TAG = "NHAC-CHO";

    public static final boolean DEBUG = true;

    private static final String HOST = "fun.local";

    public static final String HOST_API = HOST + "/nhac_cho/index.php";

    public static final String URL_GET_CATEGORIES = "/initialize/getlist";
    public static final String URL_GET_SONG = "/getsong/getlist";
    public static final String URL_GET_SONG_BY_ALBUM = "/getsong/songbyalbum";
    public static final String URL_GET_SONG_BY_ARTIST = "getsong/songbyartist";

    public static final String URL_IMG="http://demo.vmodev.net/nhac_cho/mytheme/common/img/";


    public static final String TOKEN = "";



    public static final int MAX_IMAGE_WIDTH_FOR_MEMORY_CACHE = 500;
    public static final int MAX_IMAGE_HEIGHT_FOR_MEMORY_CACHE = 500;

    public static final int CACHE_SIZE_IMAGE_ON_MEMORY = 10 * 1024 * 1024;
    public static final int CACHE_SIZE_IMAGE_ON_DISC = 10 * 1024 * 1024;

    public static final long EXIT_TIMEOUT_SECOND = 3;

    public static final String LOGIN_PATH = "/user/login";
}
