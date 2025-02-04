package org.grzechu3o3;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
       /*
        //ustawienie daty
       ScrapTimetable.setDate(LocalDate.now().toString());
       //wybor grupy
       ScrapTimetable.setId("-34");


       Root timetable = ScrapTimetable.post();
       Teachers teachers = TeacherShortcuts.get();
       Classrooms classrooms = ClassroomShortcuts.get();
       Subjects subjects = SubjectShortcuts.get();


        if (timetable != null) {
            List<TT_template> lessons = timetable.response.ttItems;

            Map<String, String> teacherMap = new HashMap<>();
            Map<String, String> classMap = new HashMap<>();
            Map<String, String> subjectMap = new HashMap<>();

            if(teachers != null && teachers.response != null) {
                for(Teachers.Table tab : teachers.response.tables) {
                    if("teachers".equals(tab.id)) {
                        for(TeacherTemplate teacher : tab.data) {
                            teacherMap.put(teacher.id, teacher.shortName);
                        }
                    }
                }
            }
            if(classrooms != null && classrooms.response != null) {
                for(Classrooms.Table tab : classrooms.response.tables) {
                    if("classrooms".equals(tab.id)) {
                        for(ClassTemplate classr : tab.data) {
                            classMap.put(classr.id, classr.name);
                    }
                }
            }
        }
            if(subjects != null && subjects.response != null) {
            for(Subjects.Table tab : subjects.response.tables) {
                if("subjects".equals(tab.id)) {
                    for(SubjectTemplate sub : tab.data) {
                        subjectMap.put(sub.id, sub.name);
                    }
                }
            }
        }




        for(TT_template lesson : lessons) {
            if(subjectMap.containsKey(lesson.subjectId)) {
                System.out.println("Subject: " + subjectMap.get(lesson.subjectId));
            }
            System.out.println("Date: " + lesson.date);
            System.out.println("Time: " + lesson.startTime + "-" + lesson.endTime);
            if(lesson.classroomIds != null && lesson.classroomIds.length > 0) {
                String classroom = lesson.classroomIds[0];
                if(classMap.containsKey(classroom)) {
                    classroom = classMap.get(classroom);
                }
                System.out.println("Room: " + classroom);
            }

            System.out.println("Groups: " + String.join(",", lesson.groupNames));
            if(lesson.teacherIds != null && lesson.teacherIds.length > 0) {
                String teach = lesson.teacherIds[0];
                if(teacherMap.containsKey(teach)) {
                    teach = teacherMap.get(teach);
                }
                System.out.println("Teacher: " + teach);
            }
            System.out.println("---------");
        }

        } else {
            System.out.println("Couldn't get timetable");
        }

*/
        System.out.println();

    }
}