package org.grzechu3o3;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends ListenerAdapter {


    public static void main(String[] args) {
        String token = "";
        JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Bot())
                .build();
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("bot ready!");
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        User author = event.getAuthor();

        if(author.isBot()) return;

        if(msg.equalsIgnoreCase("!plan")) {

            ScrapTimetable.setDate(LocalDate.now().plusDays(LocalTime.now().getHour() >= 19 ? 1 : 0).toString());


            ScrapTimetable.setId("-34");
            Root timetable = ScrapTimetable.post();
            Teachers teachers = TeacherShortcuts.get();
            Classrooms classrooms = ClassroomShortcuts.get();
            Subjects subjects = SubjectShortcuts.get();

            if(timetable != null) {
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

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("\uD83D\uDCC5 " + ScrapTimetable.date);
                embed.setColor(Color.YELLOW);

                int lessonNumber = 1;

                for(TT_template lesson : lessons) {
                    StringBuilder lessonDetails = new StringBuilder();

                    if(subjectMap.containsKey(lesson.subjectId)) {
                        lessonDetails.append("**Subject**: ").append(subjectMap.get(lesson.subjectId)).append("\n");
                    }
                    lessonDetails.append("**Time**: ").append(lesson.startTime).append("-").append(lesson.endTime).append("\n");

                    if(lesson.classroomIds != null && lesson.classroomIds.length > 0) {
                        String classroom = lesson.classroomIds[0];
                        if(classMap.containsKey(classroom)) {
                            classroom = classMap.get(classroom);
                        }
                        lessonDetails.append("**Classroom**: ").append(classroom).append("\n");
                    }

                    if(lesson.teacherIds != null && lesson.teacherIds.length > 0) {
                        String teacher = lesson.teacherIds[0];
                        if(teacherMap.containsKey(teacher)) {
                            teacher = teacherMap.get(teacher);
                        }
                        lessonDetails.append("**Teacher**: ").append(teacher).append("\n");
                    }

                    embed.addField("Lesson "+lessonNumber, lessonDetails.toString(), false);
                    lessonNumber++;
                }
                channel.sendMessageEmbeds(embed.build()).queue();
            }
            else {
                channel.sendMessage("Couldn't get timetable!").queue();
            }
        }
    }

}
