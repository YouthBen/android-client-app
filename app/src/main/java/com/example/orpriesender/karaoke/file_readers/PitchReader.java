package com.example.orpriesender.karaoke.file_readers;

import com.example.orpriesender.karaoke.model.Pitch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Or Priesender on 12-Jan-18.
 */

public class PitchReader {
    public static List<Pitch> readPitchesFromFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Pitch> result = new LinkedList<>();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(pitchFromLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private static Pitch pitchFromLine(String line) {
        String[] seperated = line.split(",");
        return new Pitch(Float.parseFloat(seperated[1]), Float.parseFloat(seperated[0]), Float.parseFloat(seperated[2]));
    }
}

