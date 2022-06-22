package com.proj.assgn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class App1 {
    public static void main(String[] args) throws Exception {
        String path = args[0];
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        ArrayList<String> startTime = new ArrayList<>();
        ArrayList<String> endTime = new ArrayList<>();
        String sCurrentline;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while ((sCurrentline = br.readLine()) != null) {
            String[] lineValues = sCurrentline.split("-");
            startTime.add(lineValues[0]);
            endTime.add(lineValues[1]);
        }
        br.close();
        List<Date> start = new ArrayList<>(startTime.size());
        List<Date> end = new ArrayList<>(endTime.size());
        for (String dateString : startTime) {
            start.add(sdf.parse(dateString));
        }
        for (String dateString : endTime) {
            end.add(sdf.parse(dateString));
        }
        int h = minimumNumberOfMeetingRooms(start, end);
        System.out.println(h);
    }

    public static int minimumNumberOfMeetingRooms(List<Date> start, List<Date> end) {

        start.stream().sorted();
        end.stream().sorted();

        int l = start.size();
        int i = 1;
        int j = 0;

        int minMeetingRoomsRequired = 1;
        int noOfOngoingMeetings = 1;

        while (i < l && j < l) {
            if (end.get(j).getTime() > start.get(i).getTime()) {
                noOfOngoingMeetings++;
                if (minMeetingRoomsRequired < noOfOngoingMeetings) {
                    minMeetingRoomsRequired = noOfOngoingMeetings;
                }
                i++;
            } else {
                noOfOngoingMeetings--;
                j++;
            }
        }

        return minMeetingRoomsRequired;
    }

}
