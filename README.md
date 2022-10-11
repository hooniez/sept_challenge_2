# sept_challenge2
The multiple commits to the master branch are only made for the purpose of getting the project ready with the CI pipeline working as expected. After everything is set up, I have created the develop branch and then made a feature branch to work on a particular feature. It is only when I finished implementing a feature, I’ve pushed the change to the remote feature branch and switched over to the develop branch to merge the feature branch into it, and then push the change to the develop branch without making pull requests as this is a solo project not a group project where pull requests have real utility. I have talked to Homy about this, and he said it is okay as long as I know what the right thing to do in a real project. 

I haven’t specified a relation between Person and Account using @OneToMany and @ManyToOne annotations as doing so creates a circular dependency. Also, to me, they should belong to the same microservice in the first place as those two models are too closely related to be in separate microservices. For this reason, both models are implemented without apparent relational mapping. 

For the account type, it is assumed that through the front-end implementation, no other options than the “Term Investment”, “Loan”, or “Saving” will be chosen. Therefore the backend logic for checking whether accountType is any of the three was deemed unnecessary.
