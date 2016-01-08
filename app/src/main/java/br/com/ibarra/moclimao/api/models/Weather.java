package br.com.ibarra.moclimao.api.models;

/**
 * Created by joaoibarra on 05/01/16.
 */
public class Weather {
    private Long id;
    private String main;
    private String description;
    private String icon;

    public Long getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
