# Git Protocol

Our **git** protocol, every developer should read and follow this strictly.

## Main conventions

- Avoid including files in source control that are specific to your development machine or process, such as local environments, local scripts.
- Use branches whenever we can.
- Rebase frequently, you should rebase before open a [pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests).
- Avoid resolving conflicts on Github or any hosting service, do it locally.
- Use a [pull request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests) to apply finished works into the base branch.

## Pull request

- We should rebase before open a new pull request.
- We should always use a merge commit.

## Git branching model

We use [Gitflow](https://nvie.com/posts/a-successful-git-branching-model/) as our main branching model. Here a some articles about this:
  - [The Author](https://nvie.com/posts/a-successful-git-branching-model/)
  - [Atlassian](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
  
<img src="https://user-images.githubusercontent.com/78266241/149646394-e9e5fc40-9f3e-4d05-a6b2-ead204861b69.png" height="762">

Create a local feature branch based on **main** branch. In this example, we use **develop** as our main branch for development.

```
git checkout develop
git pull origin develop
git checkout -b <branch-name>
```
Rebase frequently to incorporate upstream changes.
```
git fetch origin
git rebase origin/develop
```

### Branch
  - We use bellow branch types for different kind of tasks:
    - `master` our main branch, contains code of released versions, used for our `production environment`.
    - `develop` our main development branch, used for our `development environment`.
    - `release/` branch for release, used for `staging environment`.
    - `feature/` for implementing a new feature.
    - `chore/` for do some small works.
    - `refactor/` for refactoring code.
    - `bug/` for fixing bugs.
    - `hotfix/` for hot fixing bugs.

  - Prefix with issue tracker ID, we are using PT as our main Issue Tracker so it will be the last 3 digits (`#1632344 -> 344`).
  - We use `-` to separate words (`pull-to-refresh-posts`).
  - Branch name structure:
    - `<branch-type>/<id>-<a-short-title>`
  - Examples:
    - `release/v1.0.0`
    - `feature/344-pull-to-refresh-posts`
    - `chore/235-change-database-uri`
    - `refactor/190-refactor-user-model`
    - `bug/159-fix-err-login-with-fb`
    - `hotfix/v1.1.3`

## Commit message

- We should follow [Chris Beams' tips](https://chris.beams.io/posts/git-commit/#intro) and [Tim Pope's guide](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html) to write a good commit message.
- We should use small, simple commits, rather than using a large, complex commit for all works we have done.
- Prefixes:
  - **[f]** for *feature*
  - **[b]** for *bug*
  - **[c]** for *chore*
  - **[r]** for *refactoring*
  - With hotfix, we also use prefix **[b]** for commits.
- The first line (commit title) should between 50-72 characters, followed by a blank line. If there is any story relevent with this commit, we should use put the link to the commit.
- Other lines after the commit title, we consider they are the commit body, should have length less than 72 characters, the [reason](https://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html) here. 
- At the end of the commit message, we should use a blank line, followed by a link to the story (if has), after that, if we do pair programming, we should follow [Github co-author](https://help.github.com/en/github/committing-changes-to-your-project/creating-a-commit-with-multiple-authors) format.
- We should use a [git commit template](https://medium.com/@alex.wasik/create-a-custom-git-commit-template-84468232a459) for each project (even for git globally) to make commit messages faster and more consitent. 

- For example:
  ```
  [f] Multiple pages chatting
  
  - As a user, I want to chat with other strangers in other pages, so I can match with strangers faster, and the conversation is more funnier.
  
  https ://place.the.issue.link.here
  ```

- Git commit template:
  ```
  # Issue tracker id followed by a title, 50-72 characters
  [] 

  # Commit body, 72 characters wrapped
  - 

  # Issue tracker url
  []

  # Co-authored
  Co-authored-by: name <name@example.com>
  Co-authored-by: another-name <another-name@example.com>"
  ```
## Version number format
![image](https://user-images.githubusercontent.com/78266241/149646525-37d1e234-e300-4296-9b38-e1094f8bf750.png)
