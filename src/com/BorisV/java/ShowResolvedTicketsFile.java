package com.BorisV.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class ShowResolvedTicketsFile extends Ticket{

    public ShowResolvedTicketsFile(String desc, int p, String rep, Date date) {
        super(desc, p, rep, date);
    }

    public static void showResolvedTicketsFile() {
        try{
            FileReader reader = new FileReader("File_Resolved_Tickets.txt");
            BufferedReader bufReader = new BufferedReader(reader);

            String lines = bufReader.readLine();

            while (lines != null) {
                lines = bufReader.readLine();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
