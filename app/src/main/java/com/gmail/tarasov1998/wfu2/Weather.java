package com.gmail.tarasov1998.wfu2;

public class Weather {
    public Location location;
    Temperature temperature = new Temperature();
    MainWeather mainWeather = new MainWeather();

    public class Temperature {
        private float temp;

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }


    }

    public class MainWeather {
        private String mainWeather;

        public String getMainWeather() {
            return mainWeather;
        }

        public void setMainWeather(String mWeather) {
            this.mainWeather = mWeather;
        }


    }

}
