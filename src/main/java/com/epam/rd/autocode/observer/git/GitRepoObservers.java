package com.epam.rd.autocode.observer.git;

import java.util.*;

public class GitRepoObservers {
    private static List<WebHook> webHooks = new ArrayList<>();
    private static List<Commit> commits = new ArrayList<>();

    public static Repository newRepository() {
        return new Repository() {
            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                commits.add(commit);
                for (WebHook webHook : webHooks) {
                    webHook.onEvent(new Event(Event.Type.COMMIT, branch, commits));
                }
                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {

                Commit commit = commits.get(0);
                for (WebHook webHook : webHooks) {
                    webHook.onEvent(new Event(Event.Type.MERGE, targetBranch, List.of(commit)));
                }
                commits.remove(0);
            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName) {
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
                return List.of();
            }

            @Override
            public void onEvent(Event event) {

            }
        };
    }

    public static WebHook commitToBranchWebHook(String branchName) {
        return new WebHook() {
            List<Event>caughtEvents = new ArrayList<>();

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
                return caughtEvents;
            }

            @Override
            public void onEvent(Event event) {
                caughtEvents.add(event);
            }
        };
    }
}
