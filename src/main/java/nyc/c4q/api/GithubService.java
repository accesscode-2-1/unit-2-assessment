package nyc.c4q.api;

import retrofit.Callback;

public interface GithubService {
    void getContributors(String owner,
                         String repo,
                         Callback<Void> callback);

    void getRepositories(String user,
                         Callback<Void> callback);
}