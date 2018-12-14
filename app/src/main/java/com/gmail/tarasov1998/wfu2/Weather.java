package com.gmail.tarasov1998.wfu2;

public class Weather {
    public Location location;
    public Temperature temperature = new Temperature();



    public  class Temperature {
        private float temp;

        public float getTemp() {
            return temp;
        }
        public void setTemp(float temp) {
            this.temp = temp;
        }


    }


}
