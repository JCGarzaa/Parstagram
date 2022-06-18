# Project 3 - *Parstagram*

**Parstagram** is a photo sharing app like instagram using Parse as its backend.

Time spent: **24** hours spent in total

## User Stories

The following **required** functionality is completed:

- [X] User sees app icon in home screen.
- [X] User can sign up to create a new account using Parse authentication
- [X] User can log in to their account
- [X] The current signed in user is persisted across app restarts
- [X] User can log out of their account
- [X] User can take a photo, add a caption, and post it to "Instagram"
- [X] User can view the last 20 posts submitted to "Instagram"
- [X] User can pull to refresh the last 20 posts submitted to "Instagram"
- [X] User can tap a post to go to a Post Details activity, which includes timestamp and caption.

The following **stretch** features are implemented:

- [X] Style the login page to look like the real Instagram login page.
- [X] Style the feed to look like the real Instagram feed.
- [X] User can load more posts once they reach the bottom of the feed using endless scrolling.
- [X] User should switch between different tabs using fragments and a Bottom Navigation View.
  - [X] Feed Tab (to view all posts from all users)
  - [X] Capture Tab (to make a new post using the Camera and Photo Gallery)
  - [ ] Profile Tab (to view only the current user's posts, in a grid)
- [X] Show the username and creation time for each post
- User Profiles:
  - [ ] Allow the logged in user to add a profile photo
  - [X] Display the profile photo with each post
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- [X] User can comment on a post and see all comments for each post in the post details screen.
- [X] User can like a post and see number of likes for each post in the post details screen.

The following **additional** features are implemented:

- [X] List anything else that you can get done to improve the app functionality!
- [X] Creating two custom gradients similar to instagrams colors to improve the

Please list two areas of the assignment you'd like to **discuss further with your peers** during the next class (examples include better ways to implement something, how to extend your app in certain ways, etc):

1. Changing the toolbar
2. More practice with fragments

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://imgur.com/a/33kCPkB' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='https://imgur.com/a/NroBPmO' title='Video Walkthrough2' width='' alt='Video Walkthrough2' />
<img src='https://imgur.com/a/ieUrg5C' title='Video Walkthrough3' width='' alt='Video walkthrough3' />

[Gif1]("https://imgur.com/a/33kCPkB")
[Gif2]("https://imgur.com/a/NroBPmO")
[Gif3]("https://imgur.com/a/ieUrg5C")


## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Android Async Http Client](http://loopj.com/android-async-http/) - networking library


## Notes

I implemented the fragments for the first time ever and it was a new experience. I am still getting better at passing information between two fragments much like transferring from activity to activity.
I also faced difficulty implementing the like/unlike stretch feature by communicating to the database and updating the database so that a user can logout, change screens, or quit the app and come back and the posts they'd previously liked would still be liked.


## License

    Copyright [2022] [JC Garza]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
