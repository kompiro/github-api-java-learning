package org.kompiro;

import java.util.List;

import org.eclipse.egit.github.core.Commit;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.Tree;
import org.eclipse.egit.github.core.TreeEntry;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.DataService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Test;

public class GithubApiLearning {

	@Test
	public void github() throws Exception {
		GitHubClient client = new GitHubClient();
		client.setCredentials("kompiro", "fopcc17m");
		
		RepositoryService service = new RepositoryService();
		Repository repository = service.getRepository("kompiro", "astah-evernote-plugin");

		CommitService commitService = new CommitService();
		List<RepositoryCommit> commits = commitService.getCommits(repository);
		RepositoryCommit repositoryCommit = commits.get(0);
		String headSha = repositoryCommit.getSha();
		DataService dataService = new DataService();
		Tree tree = dataService.getTree(repository, headSha,true);
		System.out.println(tree.getUrl());
		List<TreeEntry> treeRoot = tree.getTree();
		
		for (TreeEntry treeEntry : treeRoot) {
			System.out.println(treeEntry.getPath());
		}
	}
	
}