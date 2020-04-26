package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.Notification;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.*;

/* Дополнительные параметры VM:
-Xms128m
-Xmx128m
-XX:+UseG1GC || -XX:+UseSerialGC || -XX:+UseParallelGC || -XX:+UseConcMarkSweepGC
-Xlog:gc=debug:file=/Users/anglomilian/gc/gc-%p-%t.log:tags,uptime,time,level
*/

    public class MyHWGCDemo {

        public static void main(String[] args) throws InterruptedException {
            gsMonitoring();
            OutOfMemorer myBooksLibrary = new OutOfMemorer();
            myBooksLibrary.fillLabrary();
        }

        private static void gsMonitoring() {
            Map<String, List<Notification>> gcNotifications = new HashMap<>();

            List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
            for (GarbageCollectorMXBean gcbean : gcbeans) {
                System.out.println("GC name:" + gcbean.getName());
                gcNotifications.put(gcbean.getName(), new ArrayList<>());
                NotificationEmitter emitter = (NotificationEmitter) gcbean;
                NotificationListener listener = (notification, handback) -> {
                    if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                        GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                        String gcName = info.getGcName();
                        String gcAction = info.getGcAction();
                        String gcCause = info.getGcCause();

                        long startTime = info.getGcInfo().getStartTime();
                        long duration = info.getGcInfo().getDuration();

                        System.out.println("start:" + startTime + " Name:" + gcName +
                               ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                    }
                };
                emitter.addNotificationListener(listener, null, null);
            }
        }
    }
