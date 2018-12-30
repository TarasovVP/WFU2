package com.gmail.tarasov1998.wfu2;

public class Weather {
    public Location location;
    Temperature temperature = new Temperature();
    MainWeather mainWeather = new MainWeather();
    IdWeather idWeather = new IdWeather();

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
    public class IdWeather {
        private int idWeather;

        public int getIdWeather() {
            return idWeather;
        }
        public void setIdWeather(int idWeather) {
            this.idWeather = idWeather;
        }

    }

}
