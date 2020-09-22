package ru.korchagin.POIexcel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class familyStudent {
    private String id;
    private String FIO;
    private String SKnum;
    private String room;
    private Date busyWith;
    private Date freeWith;
    private String registration;
    private Date DOB; // Date of Birth
    private String POB; // Place of Birth
    private String passportSeries;
    private String passportNumber;
    private String institute;
    private String phoneNumber;
    private List<String> familyPersons;

    private static SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");

    public familyStudent(String id, String fio, String sKnum, String room, String busyWith, String freeWith, String registration, String dob, String pob, String passportSeries, String passportNumber, String institute, String phoneNumber, String familyPersons) {
        this.id = id;
        FIO = fio;
        SKnum = sKnum;
        this.room = room;
        try {
            this.busyWith = new SimpleDateFormat("m/d/yy").parse(busyWith);
            this.freeWith = new SimpleDateFormat("m/d/yy").parse(freeWith);
            DOB = new SimpleDateFormat("m/d/yy").parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.registration = registration;
        POB = pob;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.institute = institute;
        this.phoneNumber = phoneNumber;
        this.familyPersons = new LinkedList<String>(Arrays.asList(familyPersons.split(";")));

        System.out.println("Объект создан.");
    }

    public String getId() {
        return id;
    }

    public String getFIO() {
        return FIO;
    }

    public String getSKnum() {
        return SKnum;
    }

    public String getRoom() {
        return room;
    }

    public String getBusyWith() {
        return dataFormat.format(busyWith);
    }

}
