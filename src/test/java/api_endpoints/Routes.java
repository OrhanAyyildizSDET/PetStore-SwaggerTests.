package api_endpoints;

public class Routes {
    private static final String base_url = "https://petstore.swagger.io/v2";

    //user urls **********************************************************************
    public static String post_url_user = base_url+"/user";
    public static String update_url_user = base_url+"/user/{username}";
    public static String delete_url_user = base_url+"/user/{username}";
    public static String get_url_user = base_url+"/user/{username}";

    //pet urls ************************************************************************
    public static String post_url_pet = base_url+"/pet";
    public static String update_url_pet = base_url+"/pet";
    public static String delete_url_pet = base_url+"/pet/{id}";
    public static String get_url_pet = base_url+"/pet/{id}";
}
