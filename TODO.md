#FEATURES

1. [x] ListView (@shurane)
    - [x] create it in 2 stages
        - [x] first level elements first
        - [x] second level elements afterwards
    - [x] use custom Adapter that extends `ArrayAdapter/BaseAdapter`
    - [x] rows only 10 times, looping 10 times only
    - [x] rows n times, configurable by EditText
    - [x] height of row should be configurable via EditText
    - [ ] when clicked, it should invert its color
2. [ ] Network call (@amyquispe)
    - [ ] Ensure network call is off UI thread
    - [ ] Long timeout of network test (to ensure things are off UI thread)
    - [x] HTTP GET request we'll be using: `https://httpbin.org/get?custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don%27t+ask+any+questions.`
    - [x] HTTP POST request we'll be using:

        ```
        curl -X POST \
            -d "custname=james dean" \
            -d "custtel=347-8431-6090" \
            -d "custemail=hello@c4q.nyc" \
            -d "size=small" \
            -d "topping=cheese" \
            -d "delivery=22:15" \
            -d "comments=Leave it by the garage door. Don't ask any questions." \
            https://httpbin.org/post
        ```
    - [ ] HTTP GET using Square's `okhttp` library
    - [ ] HTTP POST using Square's `okhttp` library

3. [ ] API use (flickr API, Github API) (@shen)
    - [ ] FlickrService
    - [ ] FlickrResponse
    - [ ] GithubService
    - [ ] GithubResponse
    - http://www.mdswanson.com/blog/2013/12/16/reliable-android-http-testing-with-retrofit-and-mockito.html
    - when API is up
    - when API is delayed over NetworkTimeout (but not down)
    - when API is down
    - API information has been retrieved (use metadata?)
    - API information has been parsed correctly (JSON)
4. [x] flat file like JSON (@amyquispe)
    - [x] create GSON mapping
    - [x] serialize data correctly
    - [x] add row
    - [x] store to file
    - [x] retrieve from file
5. [ ] notifications (@amyquispe)
    - [x] touch to dismiss
    - [x] swipe to dismiss
    - [x] permanent
    - [x] cancel permanent
    - [ ] reupdate same notification
    - [x] with buttons

----

#BUGS
- [ ] NotificationActivity should not keep launch a new instance each time the notification is clicked

----

#EXTRA

- [ ] use travis-ci to generate and collect test reports
- [ ] use styling for bigger textviews instead of `android:textAppearance="?android:attr/textAppearanceLarge"`

