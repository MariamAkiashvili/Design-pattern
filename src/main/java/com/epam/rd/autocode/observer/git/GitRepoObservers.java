package com.epam.rd.autocode.observer.git;

import com.sun.source.tree.LiteralTree;

import java.util.*;

public class GitRepoObservers {

    private static List<Event> events = new ArrayList<>();
    private static Map<String, List<Commit>> branches = new HashMap<>();
    private static List<WebHook> webHooks = new ArrayList<>();

    public static Repository newRepository(){
        return new Repository() {
            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit =  new Commit(author, changes);
                Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));
                for(WebHook webHook : webHooks){
                    webHook.onEvent(event);
                }
                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                for (WebHook webHook : webHooks){
                    webHook.onEvent(new Event(Event.Type.MERGE,webHook.branch(), null));
                }

            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                return webHooks.getLast().caughtEvents();
//                return null;
            }

            @Override
            public void onEvent(Event event) {

            }
        };

    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHook() {
            @Override
            public String branch() {
                return branchName;
            }


            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                return events;
            }

            @Override
            public void onEvent(Event event) {

            }
        };
    }


}
