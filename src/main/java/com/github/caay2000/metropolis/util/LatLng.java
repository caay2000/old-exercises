package com.github.caay2000.metropolis.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class LatLng implements Serializable {
    private static final long serialVersionUID = 1L;
    public double lat;
    public double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng() {
    }

    public String toString() {
        return this.toUrlValue();
    }

    public String toUrlValue() {
        return String.format(Locale.ENGLISH, "%.8f,%.8f", this.lat, this.lng);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            LatLng latLng = (LatLng) o;
            return Double.compare(latLng.lat, this.lat) == 0 && Double.compare(latLng.lng, this.lng) == 0;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.lat, this.lng});
    }
}
