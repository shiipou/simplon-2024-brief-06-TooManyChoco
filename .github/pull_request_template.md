<!--
  For Work In Progress Pull Requests, please use the Draft PR feature,
  see https://github.blog/2019-02-14-introducing-draft-pull-requests/ for further details.

  Before submitting a Pull Request, please ensure you've done the following:
  - 👷‍♀️ Create small PRs. In most cases, this will be possible.
  - ✅ Provide tests for your changes.
  - 📜 Use Conventional Commit for your PR name (see https://www.conventionalcommits.org/en/v1.0.0/).
  - 📝 Use descriptive commit messages.
  - 📗 Update any related documentation and include any relevant screenshots.
-->

## About this PR
<!--
Link the related issue if any.
-->
<!--
  - What is the bug/feature you make ? (If describe in the issue, don't repeat yourself, tell us)
  - Any other changes not in the issue ? Why did you add them ?
  - Add some scope to the changes if relevant
  - Try to give as much context as possible without any technical stuff here.

  For example :
  We need some CGU in our app that the user must accept before doing anythig. See issue #42 for more informations.

  In this PR I've created :
  - The CGU structure/database with a version for each CGU
  - the API to get the current CGU version for the user.
  - The business logic to validate the CGU
  What is Out of scope :
  - The mix task to create a new CGU
  - The Admin API to manage the CGU

  This is only back-end related stuff. For the front-end part, see this issue : #1337
-->

## Technical highlight/advice
<!--
    This part is for technical stuff.
    - Tell us what did you change to implement the feature or fix the bug.
    - Don't detail it too much if it's simple stuff.
    - Add more information if you made significant changes that can affect others.

    For example :
    To create the new API, I've created a new `CGUController` and a `CGU` Context.
    2 new routes :
     - GET /api/cgu
     - POST /api/cgu/accept
    I've created a new table in the database with a migration.
    The CGU is linked to the user (Many to Many).
    I've added the rule to deny anyone that did not accept the CGU.
-->

## How to test my changes
<!--
  - How to start the project. Any other dependancies to update ? Any database migration ?
  - Step-by-step guide to reproduce the bug or test the new feature.

  Try to be rather explicit but keep it as simple as possible.

  Example :
  - On server :
    - `git checkout implement-cgu-validation`
    - `mix ecto.reset`
    - `mix ecto.migrate`
    - `mix phx.server`
  Use the thunder client in vscode :
  - Register/Login (get the token)
  - Try to access this api without validating CGU : GET /api/apps. It should fail.
  - Validate the CGU ising the API POST /api/cgu/accept
  - Access the API agail, now it should work.
-->

## Checklist
- [ ] I didn't over-scope my PR
- [ ] My PR title matches the [commit convention](https://www.conventionalcommits.org/en/v1.0.0/)
- [ ] I did not include breaking changes
- [ ] I made my own code-review before requesting one

### I included unit tests that cover my changes
- [ ] 👍 yes
- [ ] 🙅 no, because they aren't needed
- [ ] 🙋 no, because I need help

### I added/updated the documentation about my changes
- [ ] 📜 README.md
- [ ] 📕 docs/*.md
- [ ] 📓 other
- [ ] 🙅 no documentation needed
