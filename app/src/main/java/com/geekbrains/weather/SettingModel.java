package com.geekbrains.weather;

public class SettingModel {
    String cityName = "Moscow";
    boolean check_info = true;
    private static SettingModel instance;

    static  SettingModel getInstance() {
        instance = instance == null? new SettingModel() : instance;
        return instance;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isCheck_info() {
        return check_info;
    }

    public void setCheck_info(boolean check_info) {
        this.check_info = check_info;
    }

    public static void setInstance(SettingModel instance) {
        SettingModel.instance = instance;
    }


}
