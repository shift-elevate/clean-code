package com.cleancode.domain;

/**
 * Value object representing an appointment.
 * Encapsulates appointment date and type as related data.
 */
public class Appointment {
    private final String date;
    private final String type;

    public Appointment(String date, String type) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment date cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment type cannot be null or empty");
        }
        this.date = date.trim();
        this.type = type.trim();
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Appointment{date='%s', type='%s'}", date, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return date.equals(that.date) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return 31 * date.hashCode() + type.hashCode();
    }
}
