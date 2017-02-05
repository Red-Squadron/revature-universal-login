test123
To initialize local repo:

--cd destination folder
--git clone https://gitlab.com/Revature_SDET_1611/Revature_Universal_Login.git



To make a branch:

--git checkout -b [branchname]

Please make branchname somewhat descriptive of what you plan to change.



To Sync gitlab with your branch:

--git add [path to changed file]

--git commit -m 'Single Descriptive Sentence about your commit'

--git push origin [branchname]

This push line will create your branch if it doesn't exist already, and will add the commit.


Updating your local currently changing branch to reflect changes to master:

--git merge origin/master

If you have conflicts, you'll need to resolve them. Hopefully we can avoid that issue as much as possible.



Merging your completed branch with the gitlab repo:

Use the github webpage to send a merge request, don't mess about in git bash.



Setting your local back to master to branch out again:

--git checkout master
--git branch -d [branchname] (Deletes local copy of separate branch, only do this after you've merged)
--git pull (to ensure you're up to date with master before branching again)

Then refer to "To make a branch" again.
