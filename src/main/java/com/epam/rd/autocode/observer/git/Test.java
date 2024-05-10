package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    private static final Map<String, List<Commit>> branches = new HashMap<>();
    public static Repository newRepository(){


        return new Repository() {
            private List<WebHook> webHooks = new ArrayList<>();

            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
//                Commit commit = new Commit(author, changes);
//                for(WebHook webHook : webHooks){
//                    webHook.onEvent(new Event(Event.Type.COMMIT, branch, List.of(commit)));
//                }
//
//                return commit;

                List<Commit> commits = branches.getOrDefault(branch, new ArrayList<>());
                Commit newCommit = new Commit(author, changes);
                commits.add(newCommit);
                branches.put(branch, commits);
                return newCommit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                List<Commit> sourceCommits = branches.getOrDefault(sourceBranch, new ArrayList<>());
                List<Commit> targetCommits = branches.getOrDefault(targetBranch, new ArrayList<>());
                targetCommits.addAll(sourceCommits);
                branches.put(targetBranch, targetCommits);
                branches.remove(sourceBranch);
            }
        };
    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return null;

    }

    public static WebHook commitToBranchWebHook(String branchName){
        return null;

    }


}
