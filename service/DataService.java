package com.example.mvcdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mvcdemo.model.*;
import com.example.mvcdemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

  private final ContributorRepository contributorRepository;
  private final IssueRepository issueRepository;
  private final ReleaseRepository releaseRepository;
  private final CommitRepository commitRepository;

  @Autowired
  public DataService(ContributorRepository contributorRepository, IssueRepository issueRepository,
                       ReleaseRepository releaseRepository, CommitRepository commitRepository) {
    this.contributorRepository = contributorRepository;
    this.issueRepository = issueRepository;
    this.releaseRepository = releaseRepository;
    this.commitRepository = commitRepository;
  }

  public List<Contributor> getContributors(){
        return contributorRepository.findAll();
    }
  public List<Issue> getIssues(){
        return issueRepository.findAll();
    }
  public List<Release> getReleases(){
        return releaseRepository.findAll();
    }
  public List<Commit> getCommits(){
        return commitRepository.findAll();
    }

  public void addContributors(String author,String repo){
    try {
      String temp = "https://api.github.com/repos/"+author+"/"+repo+"/contributors?per_page=100";
      URL url = null;
      url = new URL(temp);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.connect();
      BufferedReader bReader = new BufferedReader(
              new InputStreamReader(connection.getInputStream(), "UTF-8"));

      String line = null;
      StringBuilder stringBuilder = new StringBuilder();
      while ((line = bReader.readLine()) != null) {
        stringBuilder.append(line);
      }

      bReader.close();
      connection.disconnect();
      JSONArray jsonArray = JSONArray.parseArray(stringBuilder.toString());
      List<Contributor> contributorList= new ArrayList<>();
      for (Object object : jsonArray){
        JSONObject jsonObject = (JSONObject) object;
        long id = jsonObject.getLong("id");
        String name = jsonObject.getString("login");
        int contributions = jsonObject.getInteger("contributions");

        Contributor contributor = new Contributor(id,name,contributions);
        contributorList.add(contributor);

        //commit 数量前几位的 developers 信息
        System.out.println(name+" "+contributions);
      }
      contributorRepository.saveAll(contributorList);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }

    public void addIssues(String author,String repo){
        try {
            int pageNum = 1;
            int cnt = 0;
            int openCnt = 0;
            int closeCnt = 0;
            while (true){
                String temp = "https://api.github.com/repos/"+author+"/"+repo+"/issues?per_page=100&page="+pageNum;
                URL url = null;
                url = new URL(temp);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                if (stringBuilder.toString().equals("[]")){
                    break;
                }

                JSONArray jsonArray = JSONArray.parseArray(stringBuilder.toString());
                System.out.println(jsonArray.size());
                cnt += jsonArray.size();
                openCnt += jsonArray.size();
                List<Issue> issueList = new ArrayList<>();
                for (Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject) object;

                    long id = jsonObject.getLong("id");
                    String state = jsonObject.getString("state");
                    String created_at = jsonObject.getString("created_at");
                    String closed_at = jsonObject.getString("closed_at");
                    String title = jsonObject.getString("title");

                    Issue issue = new Issue(id,created_at,null,state,title);
                    issueList.add(issue);
                }
                issueRepository.saveAll(issueList);
                pageNum++;
            }
            pageNum = 1;
            while (true){
                String temp = "https://api.github.com/repos/"+author+"/"+repo+"/issues?state=closed&per_page=100&page="+pageNum;
                URL url = null;
                url = new URL(temp);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bReader.close();
                connection.disconnect();

                if (stringBuilder.toString().equals("[]")){
                    break;
                }
                JSONArray jsonArray = JSONArray.parseArray(stringBuilder.toString());
                System.out.println(jsonArray.size());
                cnt += jsonArray.size();
                openCnt += jsonArray.size();
                List<Issue> issueList = new ArrayList<>();
                for (Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject) object;

                    long id = jsonObject.getLong("id");
                    String state = jsonObject.getString("state");
                    String created_at = jsonObject.getString("created_at");
                    String closed_at = jsonObject.getString("closed_at");
                    String title = jsonObject.getString("title");

                    Issue issue = new Issue(id,created_at,closed_at,state,title);
                    issueList.add(issue);
                }
                issueRepository.saveAll(issueList);
                pageNum++;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReleases(String author,String repo){
        try {
            int pageNum = 1;
            int cnt = 0;
            int openCnt = 0;
            int closeCnt = 0;
            while (true){
                String temp = "https://api.github.com/repos/"+author+"/"+repo+"/releases?per_page=100&page="+pageNum;
                URL url = null;
                url = new URL(temp);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bReader.close();
                connection.disconnect();

                if (stringBuilder.toString().equals("[]")){
                    break;
                }
                JSONArray jsonArray = JSONArray.parseArray(stringBuilder.toString());
                System.out.println(jsonArray.size());
                cnt += jsonArray.size();
                openCnt += jsonArray.size();

                List<Release> releaseList = new ArrayList<>();
                for (Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject) object;

                    long id = jsonObject.getLong("id");

                    String published_at = jsonObject.getString("published_at");


                    Release release = new Release(id,published_at);
                    releaseList.add(release);
                }
                releaseRepository.saveAll(releaseList);
                pageNum++;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCommits(String author,String repo){
        try {
            int pageNum = 1;
            int cnt = 0;
            int openCnt = 0;
            int closeCnt = 0;
            while (true){
                String temp = "https://api.github.com/repos/"+author+"/"+repo+"/commits?per_page=100&page="+pageNum;
                URL url = null;
                url = new URL(temp);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                BufferedReader bReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));

                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bReader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                bReader.close();
                connection.disconnect();

                if (stringBuilder.toString().equals("[]")){
                    break;
                }
                JSONArray jsonArray = JSONArray.parseArray(stringBuilder.toString());
                System.out.println(jsonArray.size());
                cnt += jsonArray.size();
                openCnt += jsonArray.size();

                List<Commit> commitList = new ArrayList<>();
                for (Object object : jsonArray){
                    JSONObject jsonObject = (JSONObject) object;

                    String id = jsonObject.getString("sha");
                    String date = null;
                    if (jsonObject.getString("commit").equals("")){
                    }
                    else date = jsonObject.getString("commit").substring(40,60);

                    Commit commit = new Commit(id,date);
                    commitList.add(commit);
                }
                commitRepository.saveAll(commitList);
                pageNum++;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
